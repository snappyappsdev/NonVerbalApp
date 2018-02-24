package com.snappyappsdev.nonverbalapp.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.snappyappsdev.nonverbalapp.base.TestApplication;

import com.snappyappsdev.nonverbalapp.database.TestPecService;
import com.snappyappsdev.nonverbalapp.ui.TestScreenNavigator;


/**
 * Created by lrocha on 2/10/18.
 */

public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

    public final TestScreenNavigator screenNavigator;
    public final TestPecService pecService;

    public ControllerTestRule(Class<T> activityClass) {
        super(activityClass, true, false);
        screenNavigator = TestApplication.getComponent().screenNavigator();
        pecService = TestApplication.getComponent().pecService();
    }

    public void clearState() {
        pecService.clearErrorFlags();
        pecService.clearHoldFlags();
    }
}