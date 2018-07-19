package com.app.horizon.screens.splashscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.R;
import com.app.horizon.core.view.BaseActivity;
import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.screens.onboarding.OnBoardingActivity;
import com.app.horizon.core.managers.PrefManager;

import javax.inject.Inject;


public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

//        if (prefManager.isFirstTimeLaunch()) {
//            prefManager.setFirstTimeLaunch(true);
//            startActivity(new Intent(SplashScreenActivity.this, OnBoardingActivity.class));
//            finish();
//        } else{
//            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
//            finish();
//        }
    }

}
