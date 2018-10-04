package com.app.horizon.screens.main.profile;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.databinding.FragmentProfileBinding;
import com.app.horizon.screens.main.home.category.CategoryFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment<ProfileViewModel> {

    FragmentProfileBinding binding;
    @Inject
    ViewModelProvider.Factory factory;
    private ProfileViewModel viewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public ProfileViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
        return viewModel;
    }

    public static ProfileFragment newInstance(){
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container,
                false);
        View view = binding.getRoot();
        return view;
    }


}
