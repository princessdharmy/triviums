package com.app.horizon;

import android.util.Log;

import com.app.horizon.core.dagger.components.DaggerHorizonMainComponent;
import com.app.horizon.core.managers.ForceUpdateChecker;
import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;
import java.util.Map;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.Fabric;


public class HorizonMainApplication extends DaggerApplication {

    private static HorizonMainApplication instance;
    private static final String TAG = HorizonMainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        instance = this;

        Fresco.initialize(this);

        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        // set in-app defaults
        Map<String, Object> remoteConfigDefaults = new HashMap();
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_REQUIRED, false);
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_CURRENT_VERSION, "1.0");
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_URL,
                "https://play.google.com/store/apps/details?id=com.app.horizon");

        firebaseRemoteConfig.setDefaults(remoteConfigDefaults);
        firebaseRemoteConfig.fetch(60) // fetch every minutes
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "remote config is fetched.");
                        firebaseRemoteConfig.activateFetched();
                    }
                });
    }

    public static synchronized HorizonMainApplication getInstance() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerHorizonMainComponent.builder().create(this);
    }

}
