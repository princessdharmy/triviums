package com.app.horizon.screens.main.home.category;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.offline.entities.category.Category;

import java.util.List;

import javax.inject.Inject;


public class CategoryViewModel extends ViewModel {

    public CategoryRepository repository;
    private LiveData<List<Category>> listLiveData;

    @Inject
    public CategoryViewModel(CategoryRepository repository) {
        this.repository = repository;
    }

    LiveData<List<Category>> getCategory(){
        if(listLiveData == null)
            listLiveData = repository.getCategory();
        return listLiveData;
    }

}
