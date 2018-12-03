package com.app.horizon.screens.main.leaderboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.app.horizon.core.network.models.UserProfile;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

import javax.inject.Inject;

public class LeaderboardRepository {

    private FirebaseFirestore firestore;
    private UserProfile userProfile;
    List<DocumentSnapshot> document;
    private MutableLiveData<List<DocumentSnapshot>> usersLiveData = new MutableLiveData();


    @Inject
    public LeaderboardRepository(UserProfile userProfile, FirebaseFirestore firestore) {
        this.userProfile = userProfile;
        this.firestore = firestore;
    }

    public LiveData<List<DocumentSnapshot>> getUsers(){
        fetchUsers(response -> {
            usersLiveData.postValue(response);
        });
        return usersLiveData;
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

                }
                e.printStackTrace();
            }
        })
                .addOnFailureListener(error -> {

                });
    }

    public interface UserCallback {
        void onCallback(List<DocumentSnapshot> snapshotList);
    }

}
