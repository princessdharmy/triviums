package com.app.horizon.screens.main.home.stages;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.databinding.FragmentStagesBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StagesFragment extends BaseFragment<StagesViewModel>{

    public static final String TAG = StagesFragment.class.getSimpleName();

    FragmentStagesBinding binding;
    StagesFragmentAdapter adapter;
    RecyclerView recyclerView;
    List<Integer> totalPage = new ArrayList<>();
    String categoryId;
    @Inject
    ViewModelProvider.Factory factory;
    private StagesViewModel viewModel;

    public StagesFragment() {
        // Required empty public constructor
    }

    public static StagesFragment newInstance(){
        StagesFragment fragment = new StagesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public StagesViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(StagesViewModel.class);
        return viewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stages, container,
                false);
        View view = binding.getRoot();
        categoryId = getArguments().getString("CategoryId");
        binding.setClick(new MyHandler());
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showStage(categoryId);
    }

    private void initRecyclerView(){
        adapter = new StagesFragmentAdapter(getActivity(), totalPage, listener);
        recyclerView = binding.stageView;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Fetches stages of category
     */
    public void showStage(String categoryId){
        viewModel.getStage(categoryId).observe(this, response -> {
            int page = response.getPaging().getTotalPages().intValue();
            for(int i = 1; i <= page; i++){
                totalPage.add(i);
            }
            adapter.updateStages(totalPage);
        });

    }

    public View.OnClickListener listener = view -> {

    };

    public class MyHandler{
        public void onButtonClick(View view) {
            getActivity().onBackPressed();
        }
    }


}
