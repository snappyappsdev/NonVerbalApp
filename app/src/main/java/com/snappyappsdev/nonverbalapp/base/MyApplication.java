package com.snappyappsdev.nonverbalapp.base;

import android.app.Application;

import com.snappyappsdev.nonverbalapp.BuildConfig;
import com.snappyappsdev.nonverbalapp.di.ActivityInjector;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by lrocha on 2/1/18.
 */

public class MyApplication extends Application {

    @Inject ActivityInjector activityInjector;

    protected ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = initComponent();
        component.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
