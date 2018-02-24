package com.snappyappsdev.nonverbalapp.base;

import com.snappyappsdev.nonverbalapp.database.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lrocha on 2/1/18.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        DatabaseModule.class,
})
public interface ApplicationComponent {

    void inject(MyApplication myApplication);
}