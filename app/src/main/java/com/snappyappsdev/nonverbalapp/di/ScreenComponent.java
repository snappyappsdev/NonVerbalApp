package com.snappyappsdev.nonverbalapp.di;

import com.snappyappsdev.nonverbalapp.lifecycle.DisposableManager;

import dagger.android.AndroidInjector;

/**
 * Created by lrocha on 2/16/18.
 */

public interface ScreenComponent<T> extends AndroidInjector<T> {

    @ForScreen
    DisposableManager disposableManager();
}
