package com.app.horizon.screens.splashscreen;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.screens.authentication.login.LoginActivity;
import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.screens.main.home.category.CategoryViewModel;
import com.app.horizon.screens.onboarding.OnBoardingActivity;

import javax.inject.Inject;


public class SplashScreenActivity extends BaseActivity<SplashScreenViewModel> {

    SplashScreenViewModel viewModel;
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public SplashScreenViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(SplashScreenViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);

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
