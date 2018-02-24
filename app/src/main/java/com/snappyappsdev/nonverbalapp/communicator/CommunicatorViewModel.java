package com.snappyappsdev.nonverbalapp.communicator;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.snappyappsdev.nonverbalapp.R;
import com.snappyappsdev.nonverbalapp.di.ScreenScope;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by lrocha on 2/18/18.
 */
@ScreenScope
public class CommunicatorViewModel {

    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    CommunicatorViewModel() {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Action pecUpdated() {
        return () -> errorRelay.accept(-1);
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading Pecs");
            errorRelay.accept(R.string.api_error_pecs);
        };
    }
}