package com.app.horizon.screens.main.leaderboard;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LeaderboardFragmentProvider {

    @ContributesAndroidInjector(modules = LeaderboardFragmentModule.class)
    abstract LeaderboardFragment provideLeaderboardFragment();
}
