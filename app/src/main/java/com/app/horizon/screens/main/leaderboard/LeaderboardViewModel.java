package com.app.horizon.screens.main.leaderboard;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.online.question.FirestoreResultResponse;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import javax.inject.Inject;

public class LeaderboardViewModel extends ViewModel {

    public LiveData<List<DocumentSnapshot>> liveData;
    private LeaderboardRepository repository;

    @Inject
    public LeaderboardViewModel(LeaderboardRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<DocumentSnapshot>> getUsers(){
        if(liveData == null)
            liveData=repository.getUsers();
        return liveData;
    }
}
