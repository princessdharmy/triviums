package com.app.horizon.core.dagger.modules;

import android.content.Context;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.offline.OfflineStore;
import com.app.horizon.core.store.online.OnlineStore;
import com.app.horizon.utils.Utils;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class HorizonAppModule {

    @Singleton
    @Provides
    Context provideContext(HorizonMainApplication application){
        return application;
    }

    @Singleton
    @Provides
    MainAppStore provideMainAppStore(OnlineStore onlineStore, OfflineStore offlineStore){
        return new MainAppStore(onlineStore, offlineStore);
    }

    @Singleton
    @Provides
    Gson provideGson(){
        return new Gson();
    }

    @Singleton
    @Provides
    Utils provideUtils(Context context){
        return new Utils(context);
    }

}
