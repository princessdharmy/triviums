package com.app.horizon.screens.main.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.core.store.offline.entities.category.Category;
import com.app.horizon.databinding.CategoryListBinding;

import java.util.List;

public class CategoryFragmentAdapter extends RecyclerView.Adapter<CategoryFragmentAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private LayoutInflater layoutInflater;

    public CategoryFragmentAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CategoryListBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.category_list, parent, false);

        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.binding.setCategory(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private CategoryListBinding binding;

        public CategoryViewHolder(final CategoryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
