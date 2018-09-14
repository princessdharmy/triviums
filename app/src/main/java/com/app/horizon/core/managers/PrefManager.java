package com.app.horizon.core.managers;


import android.content.Context;
import android.content.SharedPreferences;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.utils.Constants;
import com.google.gson.Gson;

import javax.inject.Inject;

public class PrefManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Gson gson;

    @Inject
    public PrefManager(Context context, Gson gson) {
        this.gson = gson;
        sharedPreferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(Constants.IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(Constants.IS_FIRST_TIME_LAUNCH, true);
    }

    public void cacheUser(UserProfile profile){
        String user = gson.toJson(profile);
        editor.putString(Constants.CACHED_USER, user);
    }

    public UserProfile getCachedUser(){
        String user = sharedPreferences.getString(Constants.CACHED_USER, null);
        return gson.fromJson(user, UserProfile.class);
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(Constants.LOGGED_IN, false);
    }

    public void setLoggedIn(boolean isLoggedIn){
        editor.putBoolean(Constants.LOGGED_IN, isLoggedIn);
        editor.commit();
    }

}
