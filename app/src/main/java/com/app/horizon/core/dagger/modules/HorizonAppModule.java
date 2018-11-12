package com.app.horizon.core.dagger.modules;

import android.content.Context;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.offline.OfflineStore;
import com.app.horizon.core.store.online.OnlineStore;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.utils.Constants;
import com.app.horizon.utils.Utils;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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
    Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
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
