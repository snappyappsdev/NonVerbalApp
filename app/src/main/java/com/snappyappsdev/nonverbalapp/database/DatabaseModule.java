package com.snappyappsdev.nonverbalapp.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.snappyappsdev.nonverbalapp.database.model.PecCacheService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lrocha on 2/18/18.
 */

@Module
public abstract class DatabaseModule {

    @Provides
    @Singleton
    static AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "pec-database").build();
    }

    @Provides
    @Named("database_scheduler")
    static Scheduler provideDatabaseScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    static PecCacheService providePecService(@Named("database_scheduler") Scheduler scheduler, AppDatabase db){
        return new PecCacheService(scheduler,db);
    }
}

