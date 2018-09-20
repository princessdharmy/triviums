package com.app.horizon.screens.main.profile;

import com.app.horizon.core.network.models.UserProfile;

import javax.inject.Inject;

public class ProfileRepository {

    private UserProfile userProfile;


    @Inject
    public ProfileRepository(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserProfile getProfile(){
        return userProfile;
    }
}
