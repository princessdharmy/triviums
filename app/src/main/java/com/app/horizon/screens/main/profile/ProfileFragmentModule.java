package com.app.horizon.screens.main.profile;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.utils.ViewModelProviderFactory;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileFragmentModule {

    @Provides
    ProfileViewModel provideProfileViewModel(ProfileRepository repository){
        return new ProfileViewModel(repository);
    }

    @Provides
    ProfileRepository provideProfileRepository(UserProfile userProfile, FirebaseFirestore firestore){
        return new ProfileRepository(userProfile, firestore);
    }

    @Provides
    AchievementAdapter procideAchievementAdapter(Context context, List<DocumentSnapshot> achievements){
        return new AchievementAdapter(context, achievements);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(ProfileViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }
}
