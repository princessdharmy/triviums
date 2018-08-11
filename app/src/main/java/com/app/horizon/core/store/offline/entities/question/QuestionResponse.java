package com.app.horizon.core.store.offline.entities.question;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class QuestionResponse {

    @SerializedName("data")
    private List<Question> questions;

    @SerializedName("paging")
    private Paging mPaging;


    public List<Question> getData() {
        return questions;
    }

    public void setData(List<Question> data) {
        questions = data;
    }

    public Paging getPaging() {
        return mPaging;
    }

    public void setPaging(Paging paging) {
        mPaging = paging;
    }

}
