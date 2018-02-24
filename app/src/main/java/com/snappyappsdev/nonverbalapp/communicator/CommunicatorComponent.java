package com.snappyappsdev.nonverbalapp.communicator;

import com.snappyappsdev.nonverbalapp.base.ScreenModule;
import com.snappyappsdev.nonverbalapp.di.ScreenComponent;
import com.snappyappsdev.nonverbalapp.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by lrocha on 2/18/18.
 */
@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        CommunicatorScreenModule.class,
})
public interface CommunicatorComponent extends ScreenComponent<CommunicatorController> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<CommunicatorController> {

    }
}
