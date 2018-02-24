package com.snappyappsdev.nonverbalapp.details;

import com.snappyappsdev.nonverbalapp.base.ScreenModule;
import com.snappyappsdev.nonverbalapp.di.ScreenComponent;
import com.snappyappsdev.nonverbalapp.di.ScreenScope;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by lrocha on 2/21/18.
 */
@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        PecDetailsScreenModule.class,
})
public interface PecDetailsComponent extends ScreenComponent<PecDetailsController>{

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PecDetailsController> {

        @BindsInstance
        public abstract void bindPecOwner(@Named("pec_title") String pecTitle);


        @Override
        public void seedInstance(PecDetailsController instance) {
            bindPecOwner(instance.getArgs().getString(PecDetailsController.PEC_TITLE_KEY));
        }
    }
}
