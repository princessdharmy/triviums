package com.app.horizon.screens.main.home.category;

import android.arch.lifecycle.LiveData;

import com.app.horizon.core.base.BaseViewModel;
import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.offline.entities.category.Category;
import com.app.horizon.core.store.offline.entities.question.QuestionResponse;
import com.app.horizon.screens.main.home.stages.StageRepository;

import java.util.List;

import javax.inject.Inject;


public class CategoryViewModel extends BaseViewModel{

    @Inject
    public CategoryRepository repository;
    private LiveData<List<Category>> listLiveData;


    public CategoryViewModel(MainAppStore store) {
        super(store);
    }

    LiveData<List<Category>> getCategory(){
        if(listLiveData == null)
            listLiveData = repository.getCategory();
        return listLiveData;
    }

}
