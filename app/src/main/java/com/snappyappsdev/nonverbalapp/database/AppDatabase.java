package com.snappyappsdev.nonverbalapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.snappyappsdev.nonverbalapp.database.model.Pec;
import com.snappyappsdev.nonverbalapp.database.model.PecDao;

/**
 * Created by lrocha on 2/18/18.
 */

@Database(entities = Pec.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PecDao pecDao();
}
