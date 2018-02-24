package com.snappyappsdev.nonverbalapp.home;

import com.bluelinelabs.conductor.Controller;
import com.snappyappsdev.nonverbalapp.communicator.CommunicatorComponent;
import com.snappyappsdev.nonverbalapp.communicator.CommunicatorController;
import com.snappyappsdev.nonverbalapp.di.ControllerKey;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by lrocha on 2/7/18.
 */
@Module(subcomponents = {
        CommunicatorComponent.class,
})
public abstract class TestScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(CommunicatorController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindCommunicatorInjector(CommunicatorComponent.Builder builder);

}
