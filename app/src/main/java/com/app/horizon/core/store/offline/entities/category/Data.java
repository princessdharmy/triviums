
package com.app.horizon.core.store.offline.entities.category;

import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("icon_url")
    private String mIconUrl;

    @SerializedName("questions")
    private String mQuestions;


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    public String getQuestions() {
        return mQuestions;
    }

    public void setQuestions(String questions) {
        mQuestions = questions;
    }


    @Override
    public String toString() {
        return "Data{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mIconUrl='" + mIconUrl + '\'' +
                ", mQuestions='" + mQuestions + '\'' +
                '}';
    }
}
