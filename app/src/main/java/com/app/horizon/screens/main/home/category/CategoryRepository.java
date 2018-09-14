package com.app.horizon.screens.main.home.category;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.app.horizon.core.store.offline.category.Category;
import com.app.horizon.core.store.offline.category.CategoryResponse;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.core.store.online.services.FirestoreService;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class CategoryRepository {

    private ApiService apiService;
    private FirestoreService firestoreService;
    final MutableLiveData<List<Category>> mutableLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public CategoryRepository(ApiService apiService, FirestoreService firestoreService) {
        this.apiService = apiService;
        this.firestoreService = firestoreService;
    }

    public LiveData<List<Category>> getCategory() {
        if(firestoreService.getCategoryFromCloud() != null) {
            fetchCategoryFromApi();
        }

        return fetchCategoryFromCloud();
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
                                firestoreService.addCategoryToCloud(categoryResponseResponse.body());
                            }

                            @Override
                            public void onError(Throwable e) {
                            }
                        }));
    }

    public LiveData<List<Category>> fetchCategoryFromCloud() {
        Observable<DocumentSnapshot> categoryObservable = firestoreService.getCategoryFromCloud();
        disposable.add(
                categoryObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<DocumentSnapshot>() {
                            @Override
                            public void onNext(DocumentSnapshot documentSnapshot) {
                                Log.e("Next!", "Next Data to emit..." + documentSnapshot
                                .getData().toString());

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                Log.e("Completed!", "Completed Successfully...");
                            }
                        })
        );

        return mutableLiveData;
    }
}
