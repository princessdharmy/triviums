package com.app.horizon.screens.main.home.category;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.app.horizon.core.base.BaseViewModel;
import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.offline.entities.category.CategoryResponse;

import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CategoryViewModel extends BaseViewModel{


    public CategoryViewModel(MainAppStore store) {
        super(store);
    }

    /**
     * Fetch Categories
     */
    public LiveData<CategoryResponse> fetchCategory(){

        final MutableLiveData<CategoryResponse> categoryMutableLiveData = new MutableLiveData<>();
        getCompositeDisposable().add(
                getApiService().fetchCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(categoryResponse -> {
                            Collections.sort(categoryResponse.getData(), (c1, c2) ->
                                    Integer.valueOf(c2.getId()) - Integer.valueOf(c1.getId()));
                            return categoryResponse;
                        })
                        .subscribeWith(new DisposableObserver<CategoryResponse>() {
                            @Override
                            public void onNext(CategoryResponse categoryResponse) {
                                Log.e("RESULT", categoryResponse.getData().toString());
                                categoryMutableLiveData.postValue(categoryResponse);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        })
        );
        return categoryMutableLiveData;
    }
}
