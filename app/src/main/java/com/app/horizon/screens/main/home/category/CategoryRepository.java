package com.app.horizon.screens.main.home.category;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.core.store.offline.daos.CategoryDAO;
import com.app.horizon.core.store.offline.entities.category.Category;
import com.app.horizon.core.store.offline.entities.category.CategoryResponse;
import com.app.horizon.core.store.online.services.ApiService;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.android.AndroidInjection;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@MainAppScope
public class CategoryRepository {

    private ApiService apiService;
    private CategoryDAO categoryDAO;
    final MutableLiveData<List<Category>> mutableLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public CategoryRepository(ApiService apiService, CategoryDAO categoryDAO) {
        this.apiService = apiService;
        this.categoryDAO = categoryDAO;
        fetchCategoryFromApi();
        //fetchCategoryFromDb();
    }

    public void fetchCategoryFromApi() {
        Single<Response<CategoryResponse>> responseObservable = apiService.fetchCategories();
        disposable.add(
                responseObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(categoryResponse -> {
                            Collections.sort(categoryResponse.body().getData(), (c1, c2) ->
                                    Integer.valueOf(c2.getId()) - Integer.valueOf(c1.getId()));
                            return categoryResponse;
                        })
                        .subscribeWith(new DisposableSingleObserver<Response<CategoryResponse>>() {
                            @Override
                            public void onSuccess(Response<CategoryResponse> categoryResponseResponse) {
                                mutableLiveData.postValue(categoryResponseResponse.body().getData());
                                //categoryDAO.insert(categoryResponseResponse.body().getData());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("ERROR", e.getMessage());
                            }
                        }));
    }


    public void fetchCategoryFromDb() {
        Single<List<Category>> categoryObservable = categoryDAO.getAll();
        categoryObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Category> categories) {
                        Log.e("RESPONSE", categories.toString());
                        mutableLiveData.postValue(categories);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public LiveData<List<Category>> getCategory() {
        return mutableLiveData;
    }
}
