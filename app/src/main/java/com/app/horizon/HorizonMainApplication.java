package com.app.horizon;

import android.app.Activity;
import android.app.Application;

import com.app.horizon.core.dagger.components.DaggerHorizonMainComponent;
import com.app.horizon.core.dagger.components.HorizonMainComponent;
import com.app.horizon.core.dagger.modules.external.ContextModule;
import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

public class HorizonMainApplication extends Application {

    private HorizonMainComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerHorizonMainComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public HorizonMainApplication get(Activity activity){
        return (HorizonMainApplication) activity.getApplication();
    }

    public HorizonMainComponent getComponent(){
        return component;
    }
}
