package com.app.horizon.core.store.online.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.core.store.offline.category.Category;
import com.app.horizon.core.store.offline.category.CategoryResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;


@MainAppScope
public class FirestoreService {

    private FirebaseFirestore db;

    String documentId;

    @Inject
    public FirestoreService() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void addNewContact(String uId, String name, String email, String profilePic) {
        documentId = uId;

        Map<String, Object> newUser = new HashMap<>();
        newUser.put("name", name);
        newUser.put("email", email);
        newUser.put("profile", profilePic);

        db.collection("users")
                .document(documentId)
                .set(newUser)
                .addOnSuccessListener(aVoid -> Log.e("Success", "User Registered!"))
                .addOnFailureListener(e -> Log.e("Error!", e.getMessage()));
    }

    public void addCategoryToCloud(CategoryResponse response){
        db.collection("users")
                .document(documentId)
                .collection("categories")
                .document("category")
                .set(response)
                .addOnSuccessListener(aVoid -> Log.e("FirestoreService", "Category has been" +
                        " added!"))
                .addOnFailureListener(e -> Log.e("Error!", "Could not write categories to " +
                        "cloud!"));
    }

    public Observable<DocumentSnapshot> getCategoryFromCloud(){
        DocumentReference docRef = db.collection("users")
                .document(documentId)
                .collection("categories")
                .document("category");

        return Observable.create(emitter -> {
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot document = task.getResult();
                        if(document.exists()){
                            Log.e("Complete!", document.getData().toString());
                        } else {
                            Log.e("Failed!", "No such document");
                        }
                    } else {
                        Log.e("Error", task.getException().toString());
                    }
                }
            });
        });
    }

    public void saveProgressInCloud(){

    }

    public void getProgressFromCloud(){

    }
}
