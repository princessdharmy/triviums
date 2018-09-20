package com.app.horizon.screens.main.home.stage.stages;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;


import com.app.horizon.core.store.online.question.QuestionResponse;
import com.app.horizon.core.store.online.services.ApiService;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class StageRepository {

    private ApiService apiService;
    final MutableLiveData<QuestionResponse> mutableLiveData = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();

    public StageRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<QuestionResponse> fetchStages(String categoryId) {
        Single<Response<QuestionResponse>> responseObservable = apiService.fetchStages(categoryId);
        disposable.add(
                responseObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Response<QuestionResponse>>() {
                            @Override
                            public void onSuccess(Response<QuestionResponse> questionResponseResponse) {
                                mutableLiveData.postValue(questionResponseResponse.body());
                            }

                            @Override
                            public void onError(Throwable e) {
                            }
                        })
        );
        return mutableLiveData;
    }
}
