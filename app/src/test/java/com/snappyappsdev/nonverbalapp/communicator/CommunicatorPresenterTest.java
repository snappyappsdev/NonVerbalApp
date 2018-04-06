package com.snappyappsdev.nonverbalapp.communicator;

import com.snappyappsdev.nonverbalapp.database.model.Pec;
import com.snappyappsdev.nonverbalapp.database.model.PecCacheService;
import com.snappyappsdev.nonverbalapp.lifecycle.DisposableManager;
import com.snappyappsdev.nonverbalapp.ui.ScreenNavigator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.neverstoplearning.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by lrocha on 2/18/18.
 */

public class CommunicatorPresenterTest {

    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Mock
    PecCacheService pecService;
    @Mock CommunicatorViewModel viewModel;
    @Mock Consumer<Throwable> onErrorConsumer;
    @Mock Consumer<Boolean> loadingConsumer;
    @Mock ScreenNavigator screenNavigator;
    @Mock RecyclerDataSource dataSource;

    private CommunicatorPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.pecUpdated()).thenReturn(() -> {
        });
    }

    @Test
    public void reposLoaded() throws Exception {
        List<Pec> pec = setUpSuccess();
        initializePresenter();

        verify(pecService).getPecs();
        verify(dataSource).setData(pec);
        verifyZeroInteractions(onErrorConsumer);
    }

    @Test
    public void reposLoadedError() throws Exception {
        Throwable error = setUpError();
        initializePresenter();

        verify(onErrorConsumer).accept(error);
        verifyZeroInteractions(dataSource);
    }

    @Test
    public void loadingSuccess() throws Exception {
        setUpSuccess();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void loadingError() throws Exception {
        //noinspection ThrowableNotThrown
        setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

//    @Test
//    public void onPecClicked() throws Exception {
//        Pec pec = TestUtils.loadJson("mock/repos/get_repo.json", Pec.class);
//        setUpSuccess();
//        initializePresenter();
//        presenter.onPecClicked(pec);
//
//    }

    private List<Pec> setUpSuccess() {
        List<Pec> pecs = new ArrayList<>();

        for(int i = 0; i < 10 ; i++){
            Pec pec = new Pec("pec_" + i);
            pecs.add(pec);
        }

        when(pecService.getPecs()).thenReturn(Single.just(pecs));

        return pecs;
    }

    private Throwable setUpError() {
        Throwable error = new IOException();
        when(pecService.getPecs()).thenReturn(Single.error(error));

        return error;
    }

    private void initializePresenter() {
        presenter = new CommunicatorPresenter(
                viewModel,
                pecService,
                screenNavigator,
                Mockito.mock(DisposableManager.class),
                dataSource);
    }
}
