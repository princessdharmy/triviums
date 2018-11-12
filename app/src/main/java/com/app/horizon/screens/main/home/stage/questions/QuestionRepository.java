package com.app.horizon.screens.main.home.stage.questions;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.online.question.QuestionResponse;
import com.app.horizon.core.store.online.services.ApiService;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class QuestionRepository {

    private ApiService apiService;
    private FirebaseFirestore firestore;
    private UserProfile userProfile;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<QuestionResponse> mutableLiveData = new MutableLiveData<>();

    public QuestionRepository(ApiService apiService, FirebaseFirestore firestore, UserProfile userProfile) {
        this.apiService = apiService;
        this.firestore = firestore;
        this.userProfile = userProfile;
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

    public void saveQuizProgress(String categoryName, String stageNumber, int score, int totalPages){
        Map<String, Object> progressDetails = new HashMap<>();
        progressDetails.put("stageNumber", stageNumber);
        progressDetails.put("score", score);
        progressDetails.put("totalPages", totalPages);

        firestore.collection("users")
                .document(userProfile.getUserUid())
                .collection("categories")
                .document("category")
                .collection("progress")
                .document(categoryName)
                .set(progressDetails)
                .addOnSuccessListener(aVoid -> Log.e("Firestore Service",
                        "Progress details have been added!"))
                .addOnFailureListener(e -> Log.e("Error!",
                        "Could not write categories to cloud!"));
    }
}
