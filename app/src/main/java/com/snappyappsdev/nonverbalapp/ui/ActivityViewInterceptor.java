package com.snappyappsdev.nonverbalapp.ui;

import android.app.Activity;
import android.support.annotation.LayoutRes;

/**
 * Created by lrocha on 2/18/18.
 */

public interface ActivityViewInterceptor {

    void setContentView(Activity activity, @LayoutRes int layoutRes);

    void clear();

    ActivityViewInterceptor DEFAULT = new ActivityViewInterceptor() {
        @Override
        public void setContentView(Activity activity, int layoutRes) {
            activity.setContentView(layoutRes);
        }

        @Override
        public void clear() {

        }
    };
}
