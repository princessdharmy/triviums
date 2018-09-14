package com.app.horizon.core.store.offline.category;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {

    @SerializedName("data")
    private List<Category> category = new ArrayList<>();

    //This is converted to List to suit Firestore data structure
    public CategoryResponse(ArrayList<Category> category) {
        this.category = category;
    }

    public List<Category> getData() {
        return category;
    }

    public void setData(List<Category> data) {
        category = data;
    }
}
