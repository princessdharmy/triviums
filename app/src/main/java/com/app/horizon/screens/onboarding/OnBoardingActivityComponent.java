package com.app.horizon.screens.onboarding;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;


@Subcomponent(modules = {OnBoardingActivityModule.class})
public interface OnBoardingActivityComponent extends AndroidInjector<OnBoardingActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<OnBoardingActivity>{}

}