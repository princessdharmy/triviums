package com.app.horizon.screens.onboarding;

import android.content.Context;

import com.app.horizon.core.store.MainAppStore;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ayokunle Paul on 7/19/18.
 */
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

}
