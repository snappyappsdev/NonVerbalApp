package com.snappyappsdev.nonverbalapp.ui;

import dagger.Binds;
import dagger.Module;

/**
 * Created by lrocha on 2/18/18.
 */


@Module
public abstract class ActivityViewInterceptorModule {

    @Binds
    abstract ActivityViewInterceptor bindDebugActivityViewInterceptor(DebugActivityViewInterceptor activityViewInterceptor);
}
