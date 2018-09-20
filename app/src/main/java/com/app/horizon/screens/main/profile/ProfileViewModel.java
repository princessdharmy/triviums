package com.app.horizon.screens.main.profile;

import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.network.models.UserProfile;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private ProfileRepository repository;

    @Inject
    public ProfileViewModel(ProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile getUserDetails(){
        return repository.getProfile();
    }
}
