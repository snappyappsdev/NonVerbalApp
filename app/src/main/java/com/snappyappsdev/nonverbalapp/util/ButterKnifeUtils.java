package com.snappyappsdev.nonverbalapp.util;

import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Created by lrocha on 2/18/18.
 */

public class ButterKnifeUtils {

    private ButterKnifeUtils() {

    }

    public static void unbind(Unbinder unbinder) {
        if (unbinder != null) {
            try {
                unbinder.unbind();
            } catch (IllegalStateException e) {
                Timber.e(e, "Error unbinding views");
            }
        }
    }
}
