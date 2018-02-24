package com.snappyappsdev.nonverbalapp.details;

import com.snappyappsdev.nonverbalapp.lifecycle.ScreenLifecycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/**
 * Created by lrocha on 2/21/18.
 */
@Module
public abstract class PecDetailsScreenModule {
    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManagerTask(PecDetailsUiManager uiManager);
}
