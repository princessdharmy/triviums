package com.app.horizon.screens.main.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.offline.category.CategoryResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class ProfileRepository {

    private UserProfile userProfile;
    private FirebaseFirestore firestore;
    List<DocumentSnapshot> document;
    private MutableLiveData<List<DocumentSnapshot>> liveData = new MutableLiveData<>();
    private MutableLiveData<Map<String, Object>> userLiveData = new MutableLiveData<>();

    @Inject
    public ProfileRepository(UserProfile userProfile, FirebaseFirestore firestore) {
        this.userProfile = userProfile;
        this.firestore = firestore;
    }

    public UserProfile getProfile() {
        return userProfile;
    }


    public LiveData<Map<String, Object>> getProfileFromCloud() {
        getUpdatedProfile(data -> {
            userLiveData.postValue(data);
        });
        return userLiveData;
    }

    public LiveData<List<DocumentSnapshot>> getProgress() {
        fetchProgress(snapshotList -> {
            liveData.postValue(snapshotList);
        });
        return liveData;
    }



    public void getUpdatedProfile(UserUpdatesCallback callback) {
        DocumentReference docRef = firestore.collection("users")
                .document(userProfile.getUserUid());

        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {

                }
            }
            callback.onCallback(task.getResult().getData());
        });
    }

    private void fetchProgress(ProgressCallback callback) {
        CollectionReference collectionReference = firestore.collection("users")
                .document(userProfile.getUserUid())
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

    /**
     * Update profile in the database
     * @param name
     * @param photoUri
     */
    public void updateProfile(String name, String photoUri) {
        DocumentReference docRef = firestore.collection("users")
                .document(userProfile.getUserUid());

        docRef.update(
                "name", name,
                "profilePicture", photoUri)
                .addOnSuccessListener(aVoid -> {})
                .addOnFailureListener(e -> { });
    }


    public interface ProgressCallback {
        void onCallback(List<DocumentSnapshot> snapshotList);
    }

    public interface UserUpdatesCallback {
        void onCallback(Map<String, Object> data);
    }
}
