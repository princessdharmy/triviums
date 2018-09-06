package com.app.horizon.screens.main.home.stage.questions;

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

public class QuestionRepository {

    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<QuestionResponse> mutableLiveData = new MutableLiveData<>();

    public QuestionRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<QuestionResponse> fetchQuestion(String categoryId, String page){
        Single<Response<QuestionResponse>> responseSingle =
                apiService.fetchQuestions(categoryId, page);
        disposable.add(
                responseSingle.subscribeOn(Schedulers.io())
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
