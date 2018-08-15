package com.app.horizon.screens.main.home.stages;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.core.store.offline.entities.question.QuestionResponse;
import com.app.horizon.core.store.online.services.ApiService;

import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@MainAppScope
public class StageRepository {

    private ApiService apiService;
    final MutableLiveData<QuestionResponse> mutableLiveData = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();

    public StageRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<QuestionResponse> fetchStages(String categoryId){
        Single<Response<QuestionResponse>> responseObservable = apiService.fetchStages(categoryId);
        disposable.add(
                responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<QuestionResponse>>() {
                    @Override
                    public void onSuccess(Response<QuestionResponse> questionResponseResponse) {
                        Log.e("Check", String.valueOf(questionResponseResponse.body().getPaging()
                                .getTotalPages()));
                        mutableLiveData.postValue(questionResponseResponse.body());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", e.getMessage());
                    }
                })
        );
        return mutableLiveData;
    }
}
