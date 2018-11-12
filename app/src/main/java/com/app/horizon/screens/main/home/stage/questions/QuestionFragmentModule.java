package com.app.horizon.screens.main.home.stage.questions;

import android.arch.lifecycle.ViewModelProvider;

import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.utils.ViewModelProviderFactory;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class QuestionFragmentModule {

    @Provides
    QuestionViewModel provideQuestionViewModel(QuestionRepository repository){
        return new QuestionViewModel(repository);
    }

    @Provides
    QuestionRepository provideQuestionRepository(ApiService apiService, FirebaseFirestore firestore,
                                                 UserProfile userProfile){
        return new QuestionRepository(apiService, firestore, userProfile);
    }

    @Provides
    //@Named("QuestionFragment")
    ViewModelProvider.Factory provideViewModelProvider(QuestionViewModel viewModel){
        return new ViewModelProviderFactory<>(viewModel);
    }

}
