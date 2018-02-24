package com.snappyappsdev.nonverbalapp.communicator;

import com.snappyappsdev.nonverbalapp.di.ScreenScope;
import com.snappyappsdev.nonverbalapp.lifecycle.ScreenLifecycleTask;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;
import io.neverstoplearning.poweradapter.adapter.RecyclerDataSource;
import io.neverstoplearning.poweradapter.item.ItemRenderer;
import io.neverstoplearning.poweradapter.item.RecyclerItem;
import io.neverstoplearning.poweradapter.item.RenderKey;

/**
 * Created by lrocha on 2/18/18.
 */
@Module
public abstract class CommunicatorScreenModule {
    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManager(CommunicatorUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Pec")
    abstract ItemRenderer<? extends RecyclerItem> bindPecRenderer(PecRenderer repoRenderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideRecyclerDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        return new RecyclerDataSource(renderers);
    }
}
