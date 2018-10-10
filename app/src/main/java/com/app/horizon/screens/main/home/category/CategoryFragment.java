package com.app.horizon.screens.main.home.category;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.core.store.offline.category.Category;
import com.app.horizon.databinding.FragmentCategoryBinding;
import com.app.horizon.screens.main.home.stage.StageActivity;
import com.app.horizon.utils.ConnectivityReceiver;
import com.app.horizon.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment<CategoryViewModel> implements
        ConnectivityReceiver.ConnectivityReceiverListener{

    private FragmentCategoryBinding binding;
    private RecyclerView recyclerView;
    private List<Category> categoryList = new ArrayList<>();
    private CategoryFragmentAdapter adapter;
    private CompositeDisposable disposable = new CompositeDisposable();
    private RecyclerView.LayoutManager layoutManager;


    @Inject
    ViewModelProvider.Factory factory;
    private CategoryViewModel viewModel;
    @Inject
    Utils utils;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public CategoryViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(CategoryViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container,
                false);
        View view = binding.getRoot();

        initRecyclerView();

        if(checkConnection()){
            showCategory();
        } else {
            utils.showSnackbar(getActivity(), getResources().getString(R.string.newtwork_unavailable));
            showCategory();
        }


        return view;
    }

    private void initRecyclerView(){
        adapter = new CategoryFragmentAdapter(getActivity(), categoryList, categoryListener);
        recyclerView = binding.categoryView;
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Fetches list of Category
     */
    public void showCategory(){
        viewModel.getCategory().observe(getViewLifecycleOwner(), category -> {
            binding.progressBar.setVisibility(View.GONE);
            binding.loadingTxt.setVisibility(View.GONE);
            if (category != null) {
                categoryList.clear();
                categoryList.addAll(category.getData());
                adapter.notifyDataSetChanged();
            }
        });
    }

    private View.OnClickListener categoryListener = view -> {
        Category category = (Category) view.getTag();

        if(checkConnection()){
            Intent intent = new Intent(getActivity(), StageActivity.class);
            intent.putExtra("CategoryId", category.getId());
            intent.putExtra("categoryName", category.getName());
            getActivity().startActivity(intent);
        } else {
            utils.showSnackbar(getActivity(), getResources().getString(R.string.newtwork_unavailable));
        }

    };

    @Override
    public void onResume() {
        super.onResume();

        //register connection status listener
        HorizonMainApplication.getInstance().setConnectivityListener(this);
    }

    public boolean checkConnection(){
        return ConnectivityReceiver.isConnected();
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(isConnected){
            utils.showSnackbar(getActivity(), getResources().getString(R.string.newtwork_available));
        } else {
            utils.showSnackbar(getActivity(), getResources().getString(R.string.newtwork_unavailable));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }


}
