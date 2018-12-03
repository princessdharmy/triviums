package com.app.horizon.screens.main.leaderboard;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.databinding.FragmentLeaderboardBinding;
import com.app.horizon.utils.ConnectivityReceiver;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends BaseFragment<LeaderboardViewModel> {

    FragmentLeaderboardBinding binding;
    @Inject
    ViewModelProvider.Factory factory;
    private LeaderboardViewModel viewModel;
    RecyclerView recyclerView;
    PeopleAdapter adapter;
    List<DocumentSnapshot> peopleList = new ArrayList<>();
    @Inject
    ConnectivityReceiver connectivityReceiver;


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
        initLeaderRecyclerview();

        connectivityReceiver.observe(this, connectionModel -> {
            getUsers();
        });


        return view;
    }

    public void initLeaderRecyclerview() {
        adapter = new PeopleAdapter(getActivity(), peopleList);
        recyclerView = binding.peopleRecyclerview;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Gets the progress of the user
     */
    public void getUsers() {
        viewModel.getUsers().observe(getViewLifecycleOwner(), response -> {
            binding.loader.setVisibility(View.GONE);
            binding.peopleRecyclerview.setVisibility(View.VISIBLE);
            if (response != null) {
                adapter.updateData(response);
            }
        });

    }


}
