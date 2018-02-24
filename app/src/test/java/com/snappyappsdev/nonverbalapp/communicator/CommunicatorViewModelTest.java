package com.snappyappsdev.nonverbalapp.communicator;

import com.snappyappsdev.nonverbalapp.R;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import io.reactivex.observers.TestObserver;

/**
 * Created by lrocha on 2/7/18.
 */
public class CommunicatorViewModelTest {
    private CommunicatorViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        viewModel = new CommunicatorViewModel();
    }

    @Test
    public void loading() throws Exception {
        TestObserver<Boolean> loadingObserver = viewModel.loading().test();
        viewModel.loadingUpdated().accept(true);
        viewModel.loadingUpdated().accept(false);

        loadingObserver.assertValues(true, false);
    }

    @Test
    public void error() throws Exception {
        TestObserver<Integer> errorObserver = viewModel.error().test();
        viewModel.onError().accept(new IOException());
        viewModel.pecUpdated().run();

        errorObserver.assertValues(R.string.api_error_pecs, -1);
    }

}