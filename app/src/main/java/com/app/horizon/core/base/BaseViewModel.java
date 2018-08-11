package com.app.horizon.core.base;

import android.arch.lifecycle.ViewModel;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.store.online.services.ApiClient;
import com.app.horizon.core.store.online.services.ApiService;

import io.reactivex.disposables.CompositeDisposable;


public class BaseViewModel extends ViewModel {

    private MainAppStore store;
    private CompositeDisposable compositeDisposable;
    private ApiService apiService;

    public BaseViewModel(MainAppStore store) {
        this.store = store;
        this.compositeDisposable = new CompositeDisposable();
        this.apiService = ApiClient.getClient().create(ApiService.class);
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public ApiService getApiService() {
        return apiService;
    }

    public MainAppStore getStore() {
        return store;
    }
}
