package com.snappyappsdev.nonverbalapp.communicator;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.snappyappsdev.nonverbalapp.R;
import com.snappyappsdev.nonverbalapp.di.ScreenScope;
import com.snappyappsdev.nonverbalapp.lifecycle.ScreenLifecycleTask;
import com.snappyappsdev.nonverbalapp.ui.ScreenNavigator;
import com.snappyappsdev.nonverbalapp.util.ButterKnifeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lrocha on 2/18/18.
 */
@ScreenScope
public class CommunicatorUiManager extends ScreenLifecycleTask {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private Unbinder unbinder;
    private Context mContext;
    private ScreenNavigator mScreenNavigator;

    @Inject
    CommunicatorUiManager(Context context,ScreenNavigator screenNavigator) {

        mContext = context;
        mScreenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(R.string.screen_title_communicator);
        toolbar.inflateMenu(R.menu.communicator);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.add_pec:
                    Toast.makeText(mContext,"Add Pec Button pressed!",Toast.LENGTH_SHORT).show();
                    mScreenNavigator.goToPecDetails("New Pec");
                    return true;
            }
            return true;
        });
    }

    @Override
    public void onExitScope() {
        ButterKnifeUtils.unbind(unbinder);
    }
}