package com.snappyappsdev.nonverbalapp.home;

import com.snappyappsdev.nonverbalapp.di.ActivityScope;
import com.snappyappsdev.nonverbalapp.ui.ActivityViewInterceptor;
import com.snappyappsdev.nonverbalapp.ui.ActivityViewInterceptorModule;
import com.snappyappsdev.nonverbalapp.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

/**
 * Created by lrocha on 2/1/18.
 */
@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
        ActivityViewInterceptorModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity>{
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{
        @Override
        public void seedInstance(MainActivity instance) {
        }
    }
}
