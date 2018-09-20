package com.app.horizon.screens.main.profile;

import android.arch.lifecycle.ViewModelProvider;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileFragmentModule {

    @Provides
    ProfileViewModel provideProfileViewModel(ProfileRepository repository){
        return new ProfileViewModel(repository);
    }

    @Provides
    ProfileRepository provideProfileRepository(UserProfile userProfile){
        return new ProfileRepository(userProfile);
    }

    @Provides
    ProfileFragmentAdapter procideProfileFragmentAdapter(){
        return new ProfileFragmentAdapter();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(ProfileViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }
}
