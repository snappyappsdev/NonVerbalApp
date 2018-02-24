package com.snappyappsdev.nonverbalapp.base;


import com.snappyappsdev.nonverbalapp.database.DatabaseModule;
import com.snappyappsdev.nonverbalapp.database.TestDatabaseModule;
import com.snappyappsdev.nonverbalapp.database.TestPecService;
import com.snappyappsdev.nonverbalapp.ui.TestActivityViewInterceptorModule;
import com.snappyappsdev.nonverbalapp.ui.TestNavigationModule;
import com.snappyappsdev.nonverbalapp.ui.TestScreenNavigator;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lrocha on 2/7/18.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestNavigationModule.class,
        TestActivityViewInterceptorModule.class,
        TestDatabaseModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

    TestScreenNavigator screenNavigator();

    TestPecService pecService();
}