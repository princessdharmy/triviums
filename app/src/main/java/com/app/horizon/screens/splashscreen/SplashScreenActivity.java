package com.app.horizon.screens.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.horizon.R;
import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.screens.onboarding.OnBoardingActivity;
import com.app.horizon.utils.PrefManager;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;


public class SplashScreenActivity extends AppCompatActivity {

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setFirstTimeLaunch(true);
            startActivity(new Intent(SplashScreenActivity.this, OnBoardingActivity.class));
            finish();
        } else{
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }
    }

}
