package com.app.horizon.core.store.offline.entities.category;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "category_table")
public class Category {

    @SerializedName("data")
    private List<Data> mData;

    public List<Data> getData() {
        return mData;
    }

    public void setData(List<Data> data) {
        mData = data;
    }
}
