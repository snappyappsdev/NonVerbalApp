package com.snappyappsdev.nonverbalapp.base;

import android.app.Activity;

import com.snappyappsdev.nonverbalapp.home.MainActivity;
import com.snappyappsdev.nonverbalapp.home.TestMainActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by lrocha on 2/7/18.
 */
@Module(subcomponents = TestMainActivityComponent.class)
public abstract class TestActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivityInjector(TestMainActivityComponent.Builder builder);
}

