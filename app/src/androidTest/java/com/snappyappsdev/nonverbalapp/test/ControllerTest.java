package com.snappyappsdev.nonverbalapp.test;

import android.content.Intent;

import com.bluelinelabs.conductor.Controller;
import com.snappyappsdev.nonverbalapp.database.TestPecService;
import com.snappyappsdev.nonverbalapp.home.MainActivity;
import com.snappyappsdev.nonverbalapp.ui.TestScreenNavigator;

import org.junit.Rule;

/**
 * Created by lrocha on 2/10/18.
 */

public abstract class ControllerTest {

    @Rule
    public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

    protected TestPecService pecService = testRule.pecService;
    protected TestScreenNavigator screenNavigator = testRule.screenNavigator;

    public ControllerTest() {
        screenNavigator.overrideInitialController(controllerToLaunch());
    }

    protected abstract Controller controllerToLaunch();

    protected void launch() {
        launch(null);
    }

    protected void launch(Intent intent) {
        testRule.launchActivity(intent);
    }
}

