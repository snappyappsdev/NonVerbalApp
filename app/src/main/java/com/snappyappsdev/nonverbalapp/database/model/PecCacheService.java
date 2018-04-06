package com.snappyappsdev.nonverbalapp.database.model;

import com.snappyappsdev.nonverbalapp.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import timber.log.Timber;

/**
 * Created by lrocha on 2/18/18.
 */
@Singleton
public class PecCacheService {

    private final List<Pec> cachedPecs = new ArrayList<>();
    private final Scheduler scheduler;
    private final PecDao pecDao;

    public PecCacheService(Scheduler scheduler, AppDatabase appDatabase) {
        this.scheduler = scheduler;
        this.pecDao = appDatabase.pecDao();
    }

    public Single<List<Pec>> getPecs() {
        return Maybe.concat(cachedPecs(), daoPecs())
                .firstOrError()
                .subscribeOn(scheduler);
    }

    private Maybe<List<Pec>> cachedPecs() {
        return Maybe.create(e -> {
            if (!cachedPecs.isEmpty()) {
                e.onSuccess(cachedPecs);
            }
            e.onComplete();
        });
    }

    private Maybe<List<Pec>> daoPecs() {
        return pecDao.getPecs()
                .firstOrError()
                .doOnSuccess(pecs -> {
                    cachedPecs.clear();
                    cachedPecs.addAll(pecs);
                })
                .toMaybe();
    }

    public Single<Pec> getPec(String title){
        return Maybe.concat(cachedPec(title), dbPec(title))
                .firstOrError()
                .subscribeOn(scheduler);
    }

    private Maybe<Pec> cachedPec(String title){
        return Maybe.create(e -> {
            for(Pec pec : cachedPecs) {
                if(pec.getTitle().equals(title)){
                    e.onSuccess(pec);
                    break;
                }
            }
            e.onComplete();
        });
    }


    private Maybe<Pec> dbPec(String title){
        return pecDao
                .getPec(title)
                .firstOrError()
                .toMaybe();
    }

    public void clearCache() {
        cachedPecs.clear();
    }
    public void addPec(Pec pec) {
        runDbOp(() -> {
            pecDao.addPec(pec);
        });
    }

    public void deletePec(Pec pec) {
        runDbOp(() -> {
            pecDao.deletePec(pec);
        });
    }

    public void updatePec(Pec pec) {
        runDbOp(() -> {
            pecDao.updatePec(pec);
        });
    }

    public void emptyTable(){
        runDbOp(()-> {
            pecDao.emptyTable();
        });
    }

    private void runDbOp(Action action) {
        Completable.fromAction(action)
                .subscribeOn(scheduler)
                .subscribe(() -> {
                }, throwable -> Timber.e(throwable, "Error performing database operation"));
    }
}
