package com.snappyappsdev.nonverbalapp.networking;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by lrocha on 2/5/18.
 */
@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Call.Factory providesOkHttp(){
        return new OkHttpClient.Builder().build();
    }

    @Provides
    @Named("base_url")
    static String provideBaseUrl() {
        return "http://snappyappsdev.com/";
    }
}
