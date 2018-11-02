package com.app.horizon;

import com.app.horizon.core.dagger.components.DaggerHorizonMainComponent;
import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.Fabric;
import me.aartikov.alligator.AndroidNavigator;
import me.aartikov.alligator.NavigationContextBinder;
import me.aartikov.alligator.Navigator;
import me.aartikov.alligator.ScreenResolver;
import me.aartikov.alligator.navigationfactories.GeneratedNavigationFactory;
import me.aartikov.alligator.navigationfactories.NavigationFactory;


public class HorizonMainApplication extends DaggerApplication {

    private static HorizonMainApplication instance;
    private static final String TAG = HorizonMainApplication.class.getSimpleName();
    private static AndroidNavigator sAndroidNavigator;

    @Override
    public void onCreate() {
        super.onCreate();

        //Fabric for Crashlytics
        Fabric.with(this, new Crashlytics());
        sAndroidNavigator  = new AndroidNavigator(new GeneratedNavigationFactory());

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

    public static NavigationFactory getNavigationFactory() {
        return sAndroidNavigator.getNavigationFactory();
    }

    public static Navigator getNavigator() {
        return sAndroidNavigator;
    }

    public static NavigationContextBinder getNavigationContextBinder() {
        return sAndroidNavigator;
    }

    public static ScreenResolver getScreenResolver() {
        return sAndroidNavigator.getScreenResolver();
    }
}
