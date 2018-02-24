package com.snappyappsdev.nonverbalapp.details;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.snappyappsdev.nonverbalapp.R;
import com.snappyappsdev.nonverbalapp.lifecycle.ScreenLifecycleTask;
import com.snappyappsdev.nonverbalapp.ui.ScreenNavigator;
import com.snappyappsdev.nonverbalapp.util.ButterKnifeUtils;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lrocha on 2/21/18.
 */

public class PecDetailsUiManager extends ScreenLifecycleTask {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private final String pecTitle;
    private final ScreenNavigator screenNavigator;

    private Unbinder unbinder;

    @Inject
    PecDetailsUiManager(@Named("pec_title") String pecTitle, ScreenNavigator screenNavigator) {
        this.pecTitle = pecTitle;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(pecTitle);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> screenNavigator.pop());
    }

    @Override
    public void onExitScope() {
        toolbar.setNavigationOnClickListener(null);
        ButterKnifeUtils.unbind(unbinder);
    }
}