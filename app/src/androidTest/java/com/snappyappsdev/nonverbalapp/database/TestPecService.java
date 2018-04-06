package com.snappyappsdev.nonverbalapp.database;

import android.os.Handler;
import android.os.Looper;

import com.snappyappsdev.nonverbalapp.database.model.Pec;
import com.snappyappsdev.nonverbalapp.database.model.PecCacheService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by lrocha on 2/18/18.
 */
@Singleton
public class TestPecService  extends PecCacheService {

    public static final int FLAG_PECS = 1;
    public static final int FLAG_GET_PEC = 2;

    private int errorFlags;
    private int holdFlags;

    @Inject
    TestPecService(Scheduler scheduler, AppDatabase database) {
        super(scheduler,null);
    }

    @Override
    public Single<List<Pec>> getPecs() {
        if ((errorFlags & FLAG_PECS) == 0) {
            List<Pec> pecs = new ArrayList<>();
            Pec pec = new Pec(id, 1);
            pec.setTitle("pectest");
            pecs.add(pec);
            if ((holdFlags & FLAG_PECS) == FLAG_PECS) {
                return holdingSingle(pecs, FLAG_PECS);
            }
            return Single.just(pecs);
        }
        return Single.error(new IOException());
    }

    public void setErrorFlags(int errorFlags) {
        this.errorFlags = errorFlags;
    }

    public void clearErrorFlags() {
        this.errorFlags = 0;
    }

    public void setHoldFlags(int holdFlags) {
        this.holdFlags = holdFlags;
    }

    public void clearHoldFlags() {
        this.holdFlags = 0;
    }

    private <T> Single<T> holdingSingle(T result, int flag) {
        return Single.create(e -> {
            final Handler handler = new Handler(Looper.getMainLooper());
            Runnable holdRunnable = new Runnable() {
                @Override
                public void run() {
                    if ((holdFlags & flag) == flag) {
                        handler.postDelayed(this, 50);
                    } else {
                        e.onSuccess(result);
                    }
                }
            };
            holdRunnable.run();
        });
    }
}