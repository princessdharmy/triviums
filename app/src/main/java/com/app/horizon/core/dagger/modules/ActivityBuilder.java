package com.app.horizon.core.dagger.modules;

import android.app.Activity;

import com.app.horizon.screens.authentication.login.LoginActivity;
import com.app.horizon.screens.authentication.login.LoginActivityComponent;
import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.screens.main.MainActivityComponent;
import com.app.horizon.screens.onboarding.OnBoardingActivity;
import com.app.horizon.screens.onboarding.OnBoardingActivityComponent;
import com.app.horizon.screens.splashscreen.SplashScreenActivity;
import com.app.horizon.screens.splashscreen.SplashScreenActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;


@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(SplashScreenActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindSplashScreenActivity(SplashScreenActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(OnBoardingActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindOnboardingActivity(OnBoardingActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(LoginActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindLoginActivity(LoginActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindMainActivity(MainActivityComponent.Builder builder);
}
