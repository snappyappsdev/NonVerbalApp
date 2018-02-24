package com.snappyappsdev.nonverbalapp.ui;

import com.snappyappsdev.nonverbalapp.di.ActivityScope;
import com.snappyappsdev.nonverbalapp.lifecycle.ActivityLifecycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

/**
 * Created by lrocha on 2/3/18.
 */

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
