package com.app.horizon.screens.main.home.category;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.view.View;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.core.store.online.services.FirestoreService;
import com.app.horizon.utils.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoryFragmentModule {

    Context context;
    View.OnClickListener listener;

    @Provides
    CategoryViewModel categoryViewModel(CategoryRepository repository) {
        return new CategoryViewModel(repository);
    }

    @Provides
    CategoryRepository provideRepository(ApiService apiService, FirestoreService firestoreService){
        return new CategoryRepository(apiService, firestoreService);
    }

    @Provides
    CategoryFragmentAdapter provideCategoryAdapter(){
        return new CategoryFragmentAdapter(context, new ArrayList<>(), listener);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(CategoryViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }

}
