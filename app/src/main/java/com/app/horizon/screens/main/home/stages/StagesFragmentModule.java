package com.app.horizon.screens.main.home.stages;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.view.View;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.utils.ViewModelProviderFactory;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class StagesFragmentModule {

    @Provides
    StagesViewModel stagesViewModel(MainAppStore store) {
        return new StagesViewModel(store);
    }

    @Provides
    StageRepository provideRepository(ApiService apiService){
        return new StageRepository(apiService);
    }

    @Provides
    StagesFragmentAdapter provideStageAdapter(Context context, List<Integer> totalPage,
                                              View.OnClickListener listener){
        return new StagesFragmentAdapter(context, totalPage, listener);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(StagesViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }
}
