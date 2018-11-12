package com.app.horizon.screens.onboarding;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;


@Module
public class OnBoardingActivityModule {

    @Provides
    OnBoardingViewModel provideOnBoardingViewModel(MainAppStore store){
        return new OnBoardingViewModel(store);
    }

    @Provides
    OnBoardingPagerAdapter providePagerAdapter(Context context){
        return new OnBoardingPagerAdapter(context);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(OnBoardingViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }

}
