package com.app.horizon.screens.main.home.stage.stages;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;


import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.online.question.FirestoreResultResponse;
import com.app.horizon.core.store.online.question.QuestionResponse;
import com.app.horizon.core.store.online.question.QuestionResultsResponse;
import com.app.horizon.core.store.online.services.ApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class StageRepository {

    private ApiService apiService;
    private FirebaseFirestore firestore;
    private UserProfile userProfile;
    private MutableLiveData<FirestoreResultResponse> liveData = new MutableLiveData<>();
    final MutableLiveData<QuestionResultsResponse> mutableLiveData = new MutableLiveData<>();
    CompositeDisposable disposable = new CompositeDisposable();

    public StageRepository(ApiService apiService, FirebaseFirestore firestore, UserProfile userProfile) {
        this.apiService = apiService;
        this.firestore = firestore;
        this.userProfile = userProfile;
    }

    public LiveData<QuestionResultsResponse> fetchStages(String categoryId) {
        Single<Response<QuestionResponse>> responseObservable = apiService.fetchStages(categoryId);
        disposable.add(
                responseObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Response<QuestionResponse>>() {
                            @Override
                            public void onSuccess(Response<QuestionResponse> questionResponse) {
                                try {
                                    mutableLiveData.postValue(
                                            new QuestionResultsResponse(questionResponse.body(), null));
                                } catch (Exception e) {
                                    if (e instanceof FirebaseFirestoreException) {
                                        mutableLiveData.postValue(
                                                new QuestionResultsResponse(null, e.toString()));
                                    }
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                mutableLiveData.postValue(
                                        new QuestionResultsResponse(null, e.toString()));

                            }
                        })
        );
        return mutableLiveData;
    }

    public LiveData<FirestoreResultResponse> getProgress(String categoryName) {
        fetchProgress(categoryName, data -> {
            liveData.postValue(data);
        });
        return liveData;
    }

    private void fetchProgress(String categoryName, ProgressCallback callback) {
        DocumentReference docRef = firestore.collection("users")
                .document(userProfile.getUserUid())
                .collection("categories")
                .document("category")
                .collection("progress")
                .document(categoryName);

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
            }
            try {
                callback.onCallback(new FirestoreResultResponse(task.getResult().getData(), null));
            } catch (Exception e) {
                if (e instanceof FirebaseFirestoreException) {
                    callback.onCallback(new FirestoreResultResponse(null, e.getLocalizedMessage()));
                }
                e.printStackTrace();
            }
        })
                .addOnFailureListener(error -> {
                    callback.onCallback(new FirestoreResultResponse(null, error.toString()));
                });

    }

    public interface ProgressCallback {
        void onCallback(FirestoreResultResponse response);
    }
}
