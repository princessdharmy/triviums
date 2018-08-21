package com.app.horizon.screens.main.home.stage;

import dagger.Module;
import dagger.Provides;

@Module
public class StageActivityModule {

    @Provides
    StageActivityViewModel provideStageActivityViewModel(){
        return new StageActivityViewModel();
    }
}
