package com.snappyappsdev.nonverbalapp.di;

/**
 * Created by lrocha on 2/2/18.
 */


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ScreenScope {
}
