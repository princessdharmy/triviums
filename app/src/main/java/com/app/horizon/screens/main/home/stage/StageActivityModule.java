package com.app.horizon.screens.main.home.stage;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.view.View;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.screens.main.home.stage.stages.StageRepository;
import com.app.horizon.screens.main.home.stage.stages.StagesAdapter;
import com.app.horizon.utils.ViewModelProviderFactory;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class StageActivityModule {

    @Provides
    StageActivityViewModel provideStageActivityViewModel(){
        return new StageActivityViewModel();
    }

}
