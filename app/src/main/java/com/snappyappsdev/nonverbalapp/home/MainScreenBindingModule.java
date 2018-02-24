package com.snappyappsdev.nonverbalapp.home;

import com.bluelinelabs.conductor.Controller;
import com.snappyappsdev.nonverbalapp.communicator.CommunicatorComponent;
import com.snappyappsdev.nonverbalapp.communicator.CommunicatorController;
import com.snappyappsdev.nonverbalapp.details.PecDetailsComponent;
import com.snappyappsdev.nonverbalapp.details.PecDetailsController;
import com.snappyappsdev.nonverbalapp.di.ControllerKey;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by lrocha on 2/2/18.
 */

@Module(subcomponents = {
        CommunicatorComponent.class,
        PecDetailsComponent.class
})
public abstract class MainScreenBindingModule {
    @Binds
    @IntoMap
    @ControllerKey(CommunicatorController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindCommunicatorInjector(CommunicatorComponent.Builder builder);

    @Binds
    @IntoMap
    @ControllerKey(PecDetailsController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindRepoDetailsInjector(PecDetailsComponent.Builder builder);

}
