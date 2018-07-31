package com.app.horizon.core.store.offline.entities.question;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


@Entity(tableName = "question_table")
public class Question {

    @SerializedName("data")
    private List<Data> mData;

    @SerializedName("paging")
    private Paging mPaging;

    public List<Data> getData() {
        return mData;
    }

    public void setData(List<Data> data) {
        mData = data;
    }

    public Paging getPaging() {
        return mPaging;
    }

    public void setPaging(Paging paging) {
        mPaging = paging;
    }

}
