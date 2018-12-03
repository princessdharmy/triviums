package com.app.horizon;

import com.app.horizon.core.dagger.components.DaggerHorizonMainComponent;
import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.Fabric;


public class HorizonMainApplication extends DaggerApplication {

    private static HorizonMainApplication instance;
    private static final String TAG = HorizonMainApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        //Fabric for Crashlytics
        Fabric.with(this, new Crashlytics());

        //HyperLog for Remote Logging
        //HyperLog.initialize(this);
        //HyperLog.setLogLevel(Log.VERBOSE);

        instance = this;

        //Fresco for image library
        Fresco.initialize(this);
    }

    public static synchronized HorizonMainApplication getInstance() {
        return instance;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerHorizonMainComponent.builder().create(this);
    }

}
