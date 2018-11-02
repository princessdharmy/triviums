package com.app.horizon.core.dagger.builders;

import com.app.horizon.screens.authentication.LoginActivity;
import com.app.horizon.screens.authentication.LoginActivityModule;
import com.app.horizon.screens.main.MainActivity;
import com.app.horizon.screens.main.MainActivityModule;
import com.app.horizon.screens.main.home.category.CategoryFragmentProvider;
import com.app.horizon.screens.main.home.stage.StageActivity;
import com.app.horizon.screens.main.home.stage.questions.QuestionFragmentProvider;
import com.app.horizon.screens.main.home.stage.stages.StagesFragmentProvider;
import com.app.horizon.screens.main.leaderboard.LeaderboardFragmentProvider;
import com.app.horizon.screens.main.profile.ProfileFragmentProvider;
import com.app.horizon.screens.onboarding.OnBoardingActivity;
import com.app.horizon.screens.onboarding.OnBoardingActivityModule;
import com.app.horizon.screens.splashscreen.SplashScreenActivity;
import com.app.horizon.screens.splashscreen.SplashScreenActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashScreenActivityModule.class)
    abstract SplashScreenActivity contributeSplashScreenActivity();

    @ContributesAndroidInjector(modules = OnBoardingActivityModule.class)
    abstract OnBoardingActivity contributeOnboardingActivity();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            CategoryFragmentProvider.class,
            ProfileFragmentProvider.class,
            LeaderboardFragmentProvider.class})
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {
            QuestionFragmentProvider.class})
    abstract StageActivity contributeStageActivity();

}
