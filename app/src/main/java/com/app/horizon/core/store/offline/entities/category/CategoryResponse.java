package com.app.horizon.core.store.offline.entities.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {

    @SerializedName("data")
    private List<Category> category;

    public CategoryResponse(List<Category> categories) {
        this.category = categories;
    }

    public List<Category> getData() {
        return category;
    }

    public void setData(List<Category> data) {
        category = data;
    }
}
