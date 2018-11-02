package com.app.horizon.core.base;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewModel> extends DaggerFragment {

    private T viewModel;

    /**
     *
     * @return view model instance
     */
//    public abstract T getViewModel();




    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Could handle back press.
     * @return true if back press was handled
     *//*
    public boolean onBackPressed() {
        return false;
    }*/

}
