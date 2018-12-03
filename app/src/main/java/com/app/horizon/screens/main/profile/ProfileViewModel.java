package com.app.horizon.screens.main.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.network.models.UserProfile;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private ProfileRepository repository;
    private LiveData<List<DocumentSnapshot>> liveData;
    private LiveData<Map<String, Object>> userLiveData;

    @Inject
    public ProfileViewModel(ProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile getUserDetails(){
        return repository.getProfile();
    }

    public LiveData<List<DocumentSnapshot>> getLiveData() {
        if(liveData == null)
            liveData = repository.getProgress();
        return liveData;
    }

    public LiveData<Map<String, Object>> getUserLiveData() {
        if(userLiveData == null)
            userLiveData = repository.getProfileFromCloud();
        return userLiveData;
    }

    public void updateData(String name, String photoUri){
        repository.updateProfile(name, photoUri);
    }

}
