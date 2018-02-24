package com.snappyappsdev.nonverbalapp.base;

import android.app.Activity;

import com.snappyappsdev.nonverbalapp.home.MainActivity;
import com.snappyappsdev.nonverbalapp.home.MainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by lrocha on 2/1/18.
 */
@Module(subcomponents = {
        MainActivityComponent.class,
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> provideMainActivtyInjector(MainActivityComponent.Builder builder);
}

