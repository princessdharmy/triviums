package com.app.horizon.utils;

import android.arch.lifecycle.LiveData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.horizon.HorizonMainApplication;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.app.horizon.screens.main.home.stage.stages.StagesFragment.MobileData;
import static com.app.horizon.screens.main.home.stage.stages.StagesFragment.WifiData;

@Singleton
public class ConnectivityReceiver extends LiveData<ConnectionModel> {

    private Context context;


    @Inject
    public ConnectivityReceiver(Context context) {
        this.context = context;
    }


    @Override
    protected void onActive() {
        super.onActive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(networkReceiver, filter);

    }


    @Override
    protected void onInactive() {
        super.onInactive();
        context.unregisterReceiver(networkReceiver);
    }

    private BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                NetworkInfo activeNetwork = (NetworkInfo)
                        intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();

                if (isConnected) {
                    switch (activeNetwork.getType()) {
                        case ConnectivityManager.TYPE_WIFI:
                            postValue(new ConnectionModel(WifiData, true));
                            break;
                        case ConnectivityManager.TYPE_MOBILE:
                            postValue(new ConnectionModel(MobileData, true));
                            break;

                    }
                } else {
                    postValue(new ConnectionModel(0, false));
                }
            }

        }
    };

}
