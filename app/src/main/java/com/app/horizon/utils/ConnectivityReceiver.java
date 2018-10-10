package com.app.horizon.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.horizon.HorizonMainApplication;

public class ConnectivityReceiver extends BroadcastReceiver{

    public static ConnectivityReceiverListener connectivityReceiverListener;

    public ConnectivityReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();

        if(connectivityReceiverListener != null){
            connectivityReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }

    public static boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                HorizonMainApplication.getInstance().getApplicationContext().
                        getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
