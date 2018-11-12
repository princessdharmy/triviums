package com.app.horizon.screens.main.home.stage.questions;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class QuestionFragmentProvider {

    @ContributesAndroidInjector(modules = QuestionFragmentModule.class)
    abstract QuestionFragment provideQuestionFragmentFactory();
}
