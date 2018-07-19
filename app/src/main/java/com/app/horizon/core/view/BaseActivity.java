package com.app.horizon.core.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.screens.splashscreen.SplashScreenActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import dagger.android.AndroidInjection;


public class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 7/3/18 Add connectivity event emissions to @link {BaseActivity.java}
    }

    protected <T extends Activity> void inject(T activity){
        AndroidInjection.inject(activity);
    }
}
