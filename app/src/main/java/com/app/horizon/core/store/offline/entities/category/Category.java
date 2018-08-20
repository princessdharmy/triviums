
package com.app.horizon.core.store.offline.entities.category;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "category_table")
public class Category {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String mId;

    @NonNull
    @ColumnInfo(name = "category")
    @SerializedName("name")
    private String mName;

    @SerializedName("icon_url")
    private String mIconUrl;

    @SerializedName("questions")
    private String mQuestions;


    public Category(@NonNull String mId, @NonNull String mName, String mIconUrl, String mQuestions) {
        this.mId = mId;
        this.mName = mName;
        this.mIconUrl = mIconUrl;
        this.mQuestions = mQuestions;
    }

    @BindingAdapter("iconUrl")
    public static void loadImage(SimpleDraweeView view, String imageUrl){
        view.setImageURI(imageUrl);
    }
    @BindingAdapter("specialTag")
    public static void setSpecialTag(View view, Object value){
        view.setTag(value);
    }

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
