package com.snappyappsdev.nonverbalapp.communicator;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.bluelinelabs.conductor.Controller;
import com.snappyappsdev.nonverbalapp.R;
import com.snappyappsdev.nonverbalapp.database.TestPecService;
import com.snappyappsdev.nonverbalapp.database.model.Pec;
import com.snappyappsdev.nonverbalapp.test.ControllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by lrocha on 2/8/18.
 */
@RunWith(AndroidJUnit4.class)
public class CommunicatorControllerTest extends ControllerTest {

    @Test
    public void loadPecs(){
        pecService.clearErrorFlags();
        launch();

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.tv_error)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.pec_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(allOf(withId(R.id.pec_holder_title), withText("pectest"))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void loadPecsError() {
        pecService.setErrorFlags(TestPecService.FLAG_PECS);
        launch();

        onView(withId(R.id.loading_indicator)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.pec_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        onView(withId(R.id.tv_error)).check(matches(allOf(withText(R.string.api_error_pecs), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));
    }

    @Override
    protected Controller controllerToLaunch() {
        return new CommunicatorController();
    }
}

