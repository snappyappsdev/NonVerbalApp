package com.snappyappsdev.nonverbalapp.ui;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lrocha on 2/18/18.
 */

@Module
public abstract class TestActivityViewInterceptorModule {

    @Provides
    static ActivityViewInterceptor provideActivityViewInterceptor() {
        return ActivityViewInterceptor.DEFAULT;
    }
}
