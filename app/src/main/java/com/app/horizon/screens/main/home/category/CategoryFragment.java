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

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.core.store.offline.category.Category;
import com.app.horizon.databinding.FragmentCategoryBinding;
import com.app.horizon.screens.main.home.stage.StageActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment<CategoryViewModel> {

    private FragmentCategoryBinding binding;
    private RecyclerView recyclerView;
    private List<Category> categoryList = new ArrayList<>();
    private CategoryFragmentAdapter adapter;
    private CompositeDisposable disposable = new CompositeDisposable();
    private RecyclerView.LayoutManager layoutManager;


    @Inject
    ViewModelProvider.Factory factory;
    private CategoryViewModel viewModel;


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
        showCategory();
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
            if (category != null) {
                categoryList.clear();
                categoryList.addAll(category.getData());
                adapter.notifyDataSetChanged();
            }
        });
    }

    private View.OnClickListener categoryListener = view -> {
        Category category = (Category) view.getTag();

        Intent intent = new Intent(getActivity(), StageActivity.class);
        intent.putExtra("CategoryId", category.getId());
        getActivity().startActivity(intent);
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }


}
