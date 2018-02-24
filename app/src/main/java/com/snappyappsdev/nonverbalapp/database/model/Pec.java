package com.snappyappsdev.nonverbalapp.database.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.neverstoplearning.poweradapter.item.RecyclerItem;

/**
 * Created by lrocha on 2/18/18.
 */
@Entity
public class Pec implements RecyclerItem{
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pec)) return false;

        Pec pec = (Pec) o;

        if (id != pec.id) return false;
        return title != null ? title.equals(pec.title) : pec.title == null;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public Pec(String title) {
        this.title = title;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Pec:{ id:" + id + ", title:" + title + " }";
    }

    @Override
    public String renderKey() {
        return "Pec";
    }

    public void setId(long id) {
        this.id = id;
    }
}
