package com.app.horizon.screens.main.home.stages;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.BR;
import com.app.horizon.R;
import com.app.horizon.databinding.StageItemBinding;

import java.util.List;

import javax.inject.Inject;


public class StagesFragmentAdapter extends RecyclerView.Adapter<StagesFragmentAdapter.StageViewHolder> {

    Context context;
    List<Integer> totalPage;
    private View.OnClickListener listener;

    @Inject
    public StagesFragmentAdapter(Context context, List<Integer> totalPage,
                                 View.OnClickListener listener) {
        this.context = context;
        this.totalPage = totalPage;
        this.listener = listener;
    }


    @NonNull
    @Override
    public StageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.stage_item,
                viewGroup, false);
        return new StageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StageViewHolder holder, int i) {
        holder.getBinding().setNumber(totalPage.get(i));
        //holder.getBinding().stageButton.setText(String.valueOf(totalPage));
        holder.getBinding().setVariable(BR.on_click, listener);

    }


    @Override
    public int getItemCount() {
        return totalPage.size();
    }


    public class StageViewHolder extends RecyclerView.ViewHolder {

        StageItemBinding binding;

        public StageViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setTag(getBinding());
        }

        public StageItemBinding getBinding() {
            return binding;
        }
    }
}
