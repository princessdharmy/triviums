package com.app.horizon.core.dagger.modules;

import android.app.Activity;

import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.screens.main.MainActivityComponent;
import com.app.horizon.screens.onboarding.OnBoardingActivity;
import com.app.horizon.screens.onboarding.OnBoardingActivityComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by Ayokunle Paul on 7/19/18.
 */
@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(OnBoardingActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindOnboardingActivity(OnBoardingActivityComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);
}
