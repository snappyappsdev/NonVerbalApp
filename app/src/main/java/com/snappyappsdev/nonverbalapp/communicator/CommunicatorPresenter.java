package com.snappyappsdev.nonverbalapp.communicator;

import com.snappyappsdev.nonverbalapp.database.model.Pec;
import com.snappyappsdev.nonverbalapp.database.model.PecService;
import com.snappyappsdev.nonverbalapp.di.ForScreen;
import com.snappyappsdev.nonverbalapp.di.ScreenScope;
import com.snappyappsdev.nonverbalapp.lifecycle.DisposableManager;

import javax.inject.Inject;

import io.neverstoplearning.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by lrocha on 2/18/18.
 */
@ScreenScope
public class CommunicatorPresenter {

    private final CommunicatorViewModel viewModel;
    private final PecService pecService;
    private final DisposableManager disposableManager;
    private final RecyclerDataSource dataSource;

    @Inject
    CommunicatorPresenter(
            CommunicatorViewModel viewModel,
            PecService pecService,
            @ForScreen DisposableManager disposableManager,
            RecyclerDataSource dataSource) {
        this.viewModel = viewModel;
        this.pecService = pecService;
        this.disposableManager = disposableManager;
        this.dataSource = dataSource;
        loadPec();
    }

    private void loadPec() {
        disposableManager.add(pecService.getPecs()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .doOnSuccess(__ -> viewModel.pecUpdated().run())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSource::setData, viewModel.onError()));
    }

    void onPecClicked(Pec pec) {
    }
}
