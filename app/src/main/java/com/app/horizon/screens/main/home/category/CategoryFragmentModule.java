package com.app.horizon.screens.main.home.category;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.utils.ViewModelProviderFactory;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryFragmentModule {

    Context context;

    @Provides
    CategoryViewModel categoryViewModel(MainAppStore store) {
        return new CategoryViewModel(store);
    }

    @Provides
    CategoryFragmentAdapter provideCategoryAdapter(){
        return new CategoryFragmentAdapter(context, new ArrayList<>());
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(CategoryViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }
}
