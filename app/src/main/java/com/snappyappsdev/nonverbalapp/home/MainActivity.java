package com.snappyappsdev.nonverbalapp.home;

import com.bluelinelabs.conductor.Controller;
import com.snappyappsdev.nonverbalapp.R;
import com.snappyappsdev.nonverbalapp.base.BaseActivity;
import com.snappyappsdev.nonverbalapp.communicator.CommunicatorController;

public class MainActivity extends BaseActivity {

    @Override
    public Controller initialScreen() {
        return new CommunicatorController();
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }
}
