package com.app.horizon.core.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends BaseViewModel> extends DaggerFragment {

    private BaseActivity baseActivity;
    private T viewModel;

    /**
     *
     * @return view model instance
     */
    public abstract T getViewModel();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity){
            BaseActivity activity = (BaseActivity) context;
            this.baseActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return baseActivity;
    }

    private void performDependencyInjection(){
        AndroidSupportInjection.inject(this);
    }

    public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
