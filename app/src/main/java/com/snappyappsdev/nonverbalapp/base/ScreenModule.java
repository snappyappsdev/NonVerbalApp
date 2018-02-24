package com.snappyappsdev.nonverbalapp.base;

import com.snappyappsdev.nonverbalapp.di.ForScreen;
import com.snappyappsdev.nonverbalapp.di.ScreenScope;
import com.snappyappsdev.nonverbalapp.lifecycle.DisposableManager;
import com.snappyappsdev.nonverbalapp.lifecycle.ScreenLifecycleTask;

import java.util.Set;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.Multibinds;

/**
 * Created by lrocha on 2/16/18.
 */

@Module
public abstract class ScreenModule {

    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }

    @Multibinds
    abstract Set<ScreenLifecycleTask> screenLifecycleTasks();
}
