package com.app.horizon;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.app.horizon.core.dagger.components.DaggerHorizonMainComponent;
import com.app.horizon.core.dagger.components.HorizonMainComponent;
import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class HorizonMainApplication extends MultiDexApplication implements HasActivityInjector {

    private HorizonMainComponent component;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerHorizonMainComponent.builder()
                .horizonApplication(this)
                .build();
        component.inject(this);

        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public static HorizonMainApplication get(Activity activity){
        return (HorizonMainApplication) activity.getApplication();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
