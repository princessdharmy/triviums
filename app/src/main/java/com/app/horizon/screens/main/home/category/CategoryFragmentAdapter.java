package com.app.horizon.screens.main.home.category;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.BR;
import com.app.horizon.R;
import com.app.horizon.core.store.offline.entities.category.Category;
import com.app.horizon.databinding.CategoryListBinding;

import java.util.List;

import javax.inject.Inject;

public class CategoryFragmentAdapter extends RecyclerView.Adapter<CategoryFragmentAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;
    private LayoutInflater layoutInflater;
    private View.OnClickListener listener;

    @Inject
    public CategoryFragmentAdapter(Context context, List<Category> categoryList,
                                   View.OnClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View view = layoutInflater.inflate(R.layout.category_list, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.getBinding().setCategory(categoryList.get(position));
        holder.getBinding().setVariable(BR.handlers, listener);

        //riggers the View to be updated with the new values provided.
        // This method has to be run on the UI thread.
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private CategoryListBinding binding;

        public CategoryViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
            v.setTag(binding);
        }

        public CategoryListBinding getBinding(){
            return binding;
        }
    }



}
