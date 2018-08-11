package com.app.horizon.core.store.offline;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.managers.PrefManager;
import com.app.horizon.utils.Constants;

import javax.inject.Inject;


public class OfflineStore {

    private PrefManager prefManager;

    @Inject
    public OfflineStore(PrefManager manager){
        this.prefManager = manager;
    }

    public void cacheUser(@NonNull UserProfile userProfile){
        prefManager.cacheUser(userProfile);
    }

    @Nullable
    public UserProfile getCachedUser(){
        return prefManager.getCachedUser();
    }

    public void setFirstTimeLaunch(){
        prefManager.setFirstTimeLaunch(false);
    }

    public boolean isFirstTimeLaunch(){
        return prefManager.isFirstTimeLaunch();
    }

    public boolean isLoggedIn(){
        return prefManager.isLoggedIn();
    }

    public void setLoggedIn(boolean loggedIn){
        prefManager.setLoggedIn(loggedIn);
    }
}
