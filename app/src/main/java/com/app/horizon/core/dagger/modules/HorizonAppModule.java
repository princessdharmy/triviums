package com.app.horizon.core.dagger.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.offline.HorizonDatabase;
import com.app.horizon.core.store.offline.OfflineStore;
import com.app.horizon.core.store.offline.daos.CategoryDAO;
import com.app.horizon.core.store.online.OnlineStore;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.utils.Constants;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class HorizonAppModule {

    @MainAppScope
    @Provides
    Context provideContext(HorizonMainApplication application){
        return application;
    }

    @MainAppScope
    @Provides
    MainAppStore provideMainAppStore(OnlineStore onlineStore, OfflineStore offlineStore){
        return new MainAppStore(onlineStore, offlineStore);
    }

    @MainAppScope
    @Provides
    Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @MainAppScope
    @Provides
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

    @MainAppScope
    @Provides
    HorizonDatabase provideMainAppDatabase(Context context){
        return Room.databaseBuilder(context, HorizonDatabase.class, "horizon.db")
                .allowMainThreadQueries()
                .build();
    }

    @MainAppScope
    @Provides
    CategoryDAO provideCategoryDao(HorizonDatabase database){
        return database.categoryDAO();
    }

    @MainAppScope
    @Provides
    Gson provideGson(){
        return new Gson();
    }
}
