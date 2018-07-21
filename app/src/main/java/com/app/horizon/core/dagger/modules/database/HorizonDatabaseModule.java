package com.app.horizon.core.dagger.modules.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.app.horizon.core.dagger.scopes.MainAppScope;
import com.app.horizon.core.store.offline.HorizonDatabase;

import dagger.Module;
import dagger.Provides;


@Module()
public class HorizonDatabaseModule {

    @MainAppScope
    @Provides
    public HorizonDatabase provideMainAppDatabase(Context context){
        return Room.databaseBuilder(context, HorizonDatabase.class, "horizon.db")
                .allowMainThreadQueries()
                .build();
    }

}
