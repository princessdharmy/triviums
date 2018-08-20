package com.app.horizon;

import com.app.horizon.core.dagger.components.DaggerHorizonMainComponent;
import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class HorizonMainApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerHorizonMainComponent.builder().create(this);
    }

    /*public static HorizonMainApplication get(Activity activity){
        return (HorizonMainApplication) activity.getApplication();
    }*/

}
