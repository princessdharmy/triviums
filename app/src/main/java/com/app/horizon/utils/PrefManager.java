package com.app.horizon.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    //Shared pref mode
    int PRIVATE_MODE = 0;

    //Shared preferences file name
    private static final String PREF_NAME = "horizon-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}
