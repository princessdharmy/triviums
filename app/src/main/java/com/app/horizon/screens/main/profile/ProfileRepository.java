package com.app.horizon.screens.main.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.app.horizon.core.network.models.UserProfile;
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

    @Inject
    public ProfileRepository(UserProfile userProfile, FirebaseFirestore firestore) {
        this.userProfile = userProfile;
        this.firestore = firestore;
    }

    public UserProfile getProfile(){
        return userProfile;
    }

    public LiveData<List<DocumentSnapshot>> getProgress(){
        fetchProgress(snapshotList -> {
            liveData.postValue(snapshotList);
        });
        return liveData;
    }

    private void fetchProgress(ProgressCallback callback){
        CollectionReference collectionReference = firestore.collection("users")
                .document(userProfile.getUserUid())
                .collection("categories")
                .document("category")
                .collection("progress");

        collectionReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                document = task.getResult().getDocuments();
//                liveData.postValue(document);

            } else {
                Log.e("Error getting documents", task.getException().toString());
            }
            callback.onCallback(document);
        });
    }

    public interface ProgressCallback {
        void onCallback(List<DocumentSnapshot> snapshotList);
    }
}
