package com.snappyappsdev.nonverbalapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by lrocha on 2/16/18.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForScreen {
}
