package com.snappyappsdev.nonverbalapp.details;

import com.snappyappsdev.nonverbalapp.database.model.PecCacheService;
import com.snappyappsdev.nonverbalapp.di.ForScreen;
import com.snappyappsdev.nonverbalapp.lifecycle.DisposableManager;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by lrocha on 2/21/18.
 */

public class PecDetailsPresenter {
    @Inject
    PecDetailsPresenter(
            @Named("pec_title") String pecTitle,
            PecCacheService pecService,
            PecDetailsViewModel viewModel,
            @ForScreen DisposableManager disposableManager) {
    }
}
