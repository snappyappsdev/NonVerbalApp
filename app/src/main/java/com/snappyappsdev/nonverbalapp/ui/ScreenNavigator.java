package com.snappyappsdev.nonverbalapp.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

import java.util.UUID;

/**
 * Created by lrocha on 2/3/18.
 */

public interface ScreenNavigator {
    boolean pop();

    void goToPecDetails(String pecTitle);
    void goToAudioRecorder(String pecTitle);


}
