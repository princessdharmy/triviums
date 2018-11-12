package com.app.horizon.screens.main.leaderboard;

import android.arch.lifecycle.ViewModelProvider;

import com.app.horizon.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class LeaderboardFragmentModule {

    @Provides
    LeaderboardViewModel provideLeaderboardViewModel(){
        return new LeaderboardViewModel();
    }

    @Provides
    PeopleViewHolder providePeopleViewHolder(){
        return new PeopleViewHolder();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(LeaderboardViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }
}
