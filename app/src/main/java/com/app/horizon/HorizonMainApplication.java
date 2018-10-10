package com.app.horizon;

import com.app.horizon.core.dagger.components.DaggerHorizonMainComponent;
import com.app.horizon.utils.ConnectivityReceiver;
import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class HorizonMainApplication extends DaggerApplication {

    private static HorizonMainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Fresco.initialize(this);
    }

    public static synchronized HorizonMainApplication getInstance() {
        return instance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerHorizonMainComponent.builder().create(this);
    }

}
