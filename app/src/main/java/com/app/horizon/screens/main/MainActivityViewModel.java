package com.app.horizon.screens.main;

import com.app.horizon.core.base.BaseViewModel;
import com.app.horizon.core.store.MainAppStore;

import javax.inject.Inject;


public class MainActivityViewModel extends BaseViewModel {


    @Inject
    public MainActivityViewModel(MainAppStore store){
        super(store);
    }

}
