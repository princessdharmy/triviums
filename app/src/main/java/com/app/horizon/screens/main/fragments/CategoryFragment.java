package com.app.horizon.screens.main.fragments;


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
import com.app.horizon.core.store.offline.entities.category.Category;
import com.app.horizon.core.store.offline.entities.category.CategoryResponse;
import com.app.horizon.core.store.online.services.ApiClient;
import com.app.horizon.core.store.online.services.ApiService;
import com.app.horizon.databinding.FragmentCategoryBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;
    private RecyclerView recyclerView;
    private List<Category> categoryList = new ArrayList<>();
    private CategoryFragmentAdapter adapter;
    private ApiService apiService;
    private Observable<CategoryResponse> observable;
    //private Disposable disposable;

    public CategoryFragment() {
        // Required empty public constructor
    }

    private static CategoryFragment newInstance(String param1, String param2){
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = ApiClient.getClient(getActivity()).create(ApiService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container,
                false);
        View view = binding.getRoot();
        initRecyclerView();

        fetchCategory();
        return view;
    }

    private void initRecyclerView(){
        adapter = new CategoryFragmentAdapter(getActivity(), categoryList);
        recyclerView = binding.categoryView;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Fetches list of Category
     */
    public void fetchCategory(){
        observable = apiService.fetchCategories();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(categoryResponse -> {
                    Collections.sort(categoryResponse.getData(), (c1, c2) ->
                            Integer.valueOf(c2.getId()) - Integer.valueOf(c1.getId()));
                    return categoryResponse;
                })
                .subscribe(new Observer<CategoryResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CategoryResponse categoryResponse) {
                        Log.e("Wait", "OnNext...!: " + categoryResponse.getData());
                        categoryList.clear();
                        categoryList.addAll(categoryResponse.getData());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Error", "Error...!");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Result", "Completed...!");
                    }
                });
    }

}
