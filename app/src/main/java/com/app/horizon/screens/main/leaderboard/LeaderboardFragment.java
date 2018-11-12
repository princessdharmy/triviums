package com.app.horizon.screens.main.leaderboard;


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
import com.app.horizon.databinding.FragmentLeaderboardBinding;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends BaseFragment<LeaderboardViewModel> {

    FragmentLeaderboardBinding binding;
    @Inject
    ViewModelProvider.Factory factory;
    private LeaderboardViewModel viewModel;


    public LeaderboardFragment() {
        // Required empty public constructor
    }

    @Override
    public LeaderboardViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(LeaderboardViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_leaderboard, container,
                false);
        View view = binding.getRoot();
        return view;
    }


}
