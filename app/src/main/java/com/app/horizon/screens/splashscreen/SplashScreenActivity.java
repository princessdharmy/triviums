package com.app.horizon.screens.splashscreen;

import android.content.Intent;
import android.os.Bundle;

import com.app.horizon.R;
import com.app.horizon.core.view.BaseActivity;
import com.app.horizon.screens.authentication.login.LoginActivity;
import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.screens.onboarding.OnBoardingActivity;

import javax.inject.Inject;


public class SplashScreenActivity extends BaseActivity {

    @Inject
    SplashScreenViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        inject(this);

        // Checking for first time launch
        if(!viewModel.isFirstTimeLaunch()){
            if(viewModel.isLoggedIn()){
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
            } else {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();
            }
        } else {
            startActivity(new Intent(SplashScreenActivity.this, OnBoardingActivity.class));
            finish();
        }

    }

}
