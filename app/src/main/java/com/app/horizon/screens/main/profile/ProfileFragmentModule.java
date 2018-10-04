package com.app.horizon.screens.main.profile;

import android.arch.lifecycle.ViewModelProvider;

import com.app.horizon.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileFragmentModule {

    @Provides
    ProfileViewModel provideProfileViewModel(){
        return new ProfileViewModel();
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
