package com.app.horizon.screens.main.home.category;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.core.store.offline.entities.category.Category;
import com.app.horizon.databinding.FragmentCategoryBinding;
import com.app.horizon.screens.main.home.stage.StageActivity;
import com.app.horizon.screens.main.home.stage.stages.StagesFragment;

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

    @Inject
    ViewModelProvider.Factory factory;
    private CategoryViewModel viewModel;


    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(){
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showCategory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*if(savedInstanceState != null){
            categoryList = (List<Category>) savedInstanceState.getSerializable("list");
        }*/
    }

    private void initRecyclerView(){
        adapter = new CategoryFragmentAdapter(getActivity(), categoryList, categoryListener);
        recyclerView = binding.categoryView;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Fetches list of Category
     */
    public void showCategory(){
        viewModel.getCategory().observe(this, category -> {
            if (category != null) {
                categoryList.clear();
                categoryList.addAll(category);
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

    /**
     * Save list position state
     * @param outState
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) categoryList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }


}
