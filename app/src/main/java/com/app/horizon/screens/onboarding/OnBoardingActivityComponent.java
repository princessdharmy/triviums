package com.app.horizon.screens.onboarding;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by Ayokunle Paul on 7/19/18.
 */
@Subcomponent(modules = {OnBoardingActivityModule.class})
public interface OnBoardingActivityComponent extends AndroidInjector<OnBoardingActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<OnBoardingActivity>{}

}