package com.app.horizon.core.store;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.offline.OfflineStore;
import com.app.horizon.core.store.online.OnlineStore;

import javax.inject.Inject;


public class MainAppStore {

    private OnlineStore onlineStore;
    private OfflineStore offlineStore;

    @Inject
    public MainAppStore(OnlineStore onlineStore, OfflineStore offlineStore){
        this.onlineStore = onlineStore;
        this.offlineStore = offlineStore;
    }

    public void cacheUser(@NonNull UserProfile userProfile){
        offlineStore.cacheUser(userProfile);
    }

    @Nullable
    public UserProfile getCachedUser(){
        return offlineStore.getCachedUser();
    }

    public void setFirstTimeLaunch(){
        offlineStore.setFirstTimeLaunch();
    }

    public boolean isFirstTimeLaunch(){
        return offlineStore.isFirstTimeLaunch();
    }

    public boolean isLoggedIn(){
        return offlineStore.isLoggedIn();
    }

    public void setLoggedIn(boolean loggedIn){
        offlineStore.setLoggedIn(loggedIn);
    }

}
