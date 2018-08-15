package com.app.horizon.core.base;

import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.MainAppStore;

import java.lang.ref.WeakReference;


public class BaseViewModel<N> extends ViewModel {

    private MainAppStore store;
    private WeakReference<N> navigator;

    public BaseViewModel(MainAppStore store) {
        this.store = store;
    }

    public MainAppStore getStore() {
        return store;
    }

    public N getNavigator(){
        return navigator.get();
    }

    public void setNavigator(N navigator){
        this.navigator = new WeakReference<>(navigator);
    }
}
