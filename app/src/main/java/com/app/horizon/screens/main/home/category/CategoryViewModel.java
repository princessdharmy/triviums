package com.app.horizon.screens.main.home.category;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.offline.category.CategoryResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import javax.inject.Inject;


public class CategoryViewModel extends ViewModel {

    public CategoryRepository repository;
    private LiveData<CategoryResponse> listLiveData;

    @Inject
    public CategoryViewModel(CategoryRepository repository) {
        this.repository = repository;
    }

    LiveData<CategoryResponse> getCategory(){
        if(listLiveData == null)
            listLiveData = repository.getCategory();
        return listLiveData;
    }



}
