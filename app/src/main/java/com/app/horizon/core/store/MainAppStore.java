package com.app.horizon.core.store;

import com.app.horizon.core.store.offline.OfflineStore;
import com.app.horizon.core.store.online.OnlineStore;

import javax.inject.Inject;


public class MainAppStore {

    @Inject
    public MainAppStore(OnlineStore onlineStore, OfflineStore offlineStore){

    }

}
