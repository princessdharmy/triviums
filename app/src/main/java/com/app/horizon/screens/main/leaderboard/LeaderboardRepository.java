package com.app.horizon.screens.main.leaderboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.app.horizon.core.store.online.question.FirestoreResultResponse;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

import javax.inject.Inject;

public class LeaderboardRepository {

    private FirebaseFirestore firestore;
    List<DocumentSnapshot> document;
    private MutableLiveData<List<DocumentSnapshot>> usersLiveData = new MutableLiveData();
    private MutableLiveData<List<DocumentSnapshot>> progressLiveData = new MutableLiveData<>();

    @Inject
    public LeaderboardRepository(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    public LiveData<List<DocumentSnapshot>> getUsers(){
        fetchUsers(response -> {
            usersLiveData.postValue(response);
        });
        return usersLiveData;
    }

    public LiveData<List<DocumentSnapshot>> getProgress() {
        fetchProgress(snapshotList -> {
            progressLiveData.postValue(snapshotList);
        });
        return progressLiveData;
    }

    private void fetchUsers(UserCallback callback){
        CollectionReference collection = firestore.collection("users");

        collection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                document = task.getResult().getDocuments();
            }
            try {
                callback.onCallback(document);
            } catch (Exception e) {
                if (e instanceof FirebaseFirestoreException) {
                    Log.e("Exception", e.getLocalizedMessage());
                    //callback.onCallback(e.getLocalizedMessage());
                }
                e.printStackTrace();
            }
        })
                .addOnFailureListener(error -> {
                    //callback.onCallback();
                });
    }

    private void fetchProgress(ProgressCallback callback) {
        CollectionReference collectionReference = firestore.collection("users")
                .document("uuid")
                .collection("categories")
                .document("category")
                .collection("progress");

        collectionReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                document = task.getResult().getDocuments();
            }
            callback.onCallback(document);
        });
    }


    public interface UserCallback {
        void onCallback(List<DocumentSnapshot> snapshotList);
    }
    public interface ProgressCallback {
        void onCallback(List<DocumentSnapshot> snapshotList);
    }
}
