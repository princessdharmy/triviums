package com.app.horizon.screens.main;

import com.app.horizon.core.store.MainAppStore;
import com.app.horizon.core.view.BaseViewModel;

import javax.inject.Inject;


public class MainActivityViewModel extends BaseViewModel {

    private MainAppStore store;

    @Inject
    public MainActivityViewModel(MainAppStore store){
        this.store = store;
    }

}
