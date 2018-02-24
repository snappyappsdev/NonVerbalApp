package com.snappyappsdev.nonverbalapp.database.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by lrocha on 2/18/18.
 */
@Dao
public interface PecDao {
    @Query("SELECT * from pec")
    Flowable<List<Pec>> getPecs();

    @Query("SELECT * FROM pec WHERE title = :title")
    Flowable<Pec> getPec(String title);

    @Insert
    void addPec(Pec pec);

    @Delete
    void deletePec(Pec pec);

    @Query("DELETE FROM pec")
    void emptyTable();

    @Update
    void updatePec(Pec pec);
}
