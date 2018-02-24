package com.snappyappsdev.nonverbalapp.communicator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.snappyappsdev.nonverbalapp.R;
import com.snappyappsdev.nonverbalapp.base.BaseController;
import com.snappyappsdev.nonverbalapp.database.model.Pec;

import javax.inject.Inject;

import butterknife.BindView;
import io.neverstoplearning.poweradapter.adapter.RecyclerAdapter;
import io.neverstoplearning.poweradapter.adapter.RecyclerDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by lrocha on 2/18/18.
 */

public class CommunicatorController extends BaseController {

    @Inject CommunicatorPresenter presenter;
    @Inject CommunicatorViewModel viewModel;
    @Inject RecyclerDataSource dataSource;

    @BindView(R.id.pec_list) RecyclerView pecList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;

    @Override
    protected void onViewBound(View view) {
        pecList.setLayoutManager(new GridLayoutManager(view.getContext(),3));
        pecList.setAdapter(new RecyclerAdapter(dataSource));
    }


    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.loading()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(loading -> {
                    loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                    pecList.setVisibility(loading ? View.GONE : View.VISIBLE);
                    errorText.setVisibility(loading ? View.GONE : errorText.getVisibility());
                }),
                viewModel.error()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(errorRes -> {
                    if (errorRes == -1) {
                        errorText.setText(null);
                        errorText.setVisibility(View.GONE);
                    } else {
                        errorText.setVisibility(View.VISIBLE);
                        pecList.setVisibility(View.GONE);
                        errorText.setText(errorRes);
                    }
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_communicator;
    }
}
