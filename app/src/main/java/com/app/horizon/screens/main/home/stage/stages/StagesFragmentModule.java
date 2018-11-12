package com.app.horizon.screens.main.home.stage.stages;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.view.View;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.utils.ViewModelProviderFactory;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class StagesFragmentModule {

    @Provides
    StagesViewModel stagesViewModel(StageRepository repository) {
        return new StagesViewModel(repository);
    }

    @Provides
    StageRepository provideRepository(ApiService apiService, FirebaseFirestore firestore, UserProfile userProfile){
        return new StageRepository(apiService, firestore, userProfile);
    }

    @Provides
    StagesAdapter provideStageAdapter(Context context, List<Integer> totalPage,
                                      View.OnClickListener listener){
        return new StagesAdapter(context, totalPage, listener);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(StagesViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }

}
