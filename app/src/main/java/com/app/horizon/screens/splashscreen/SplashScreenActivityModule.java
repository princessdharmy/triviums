package com.app.horizon.screens.splashscreen;

import android.arch.lifecycle.ViewModelProvider;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.screens.main.home.category.CategoryFragment;
import com.app.horizon.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashScreenActivityModule {

    @Provides
    SplashScreenViewModel providesViewModel(MainAppStore store){
        return new SplashScreenViewModel(store);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(SplashScreenViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }
}
