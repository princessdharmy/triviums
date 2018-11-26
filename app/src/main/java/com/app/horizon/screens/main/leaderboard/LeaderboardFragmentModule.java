package com.app.horizon.screens.main.leaderboard;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.app.horizon.utils.ViewModelProviderFactory;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class LeaderboardFragmentModule {

    @Provides
    LeaderboardViewModel provideLeaderboardViewModel(LeaderboardRepository repository){
        return new LeaderboardViewModel(repository);
    }

    @Provides
    LeaderboardRepository provideLeaderboardRepository(FirebaseFirestore firestore){
        return new LeaderboardRepository(firestore);
    }

    @Provides
    PeopleAdapter providePeopleAdapter(Context context, List<DocumentSnapshot> peopleList){
        return new PeopleAdapter(context, peopleList);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(LeaderboardViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }
}
