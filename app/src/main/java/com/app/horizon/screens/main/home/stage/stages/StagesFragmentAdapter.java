package com.app.horizon.screens.main.home.stage.stages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    int stage;
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
    public void onBindViewHolder(@NonNull StageViewHolder holder, int position) {
        holder.getBinding().setNumber(totalPage.get(position));
        holder.getBinding().setVariable(BR.on_click, listener);

        for(int i=0; i<stage; i++){
            if(i == position){
               holder.getBinding().stageButton.setBackgroundResource(R.drawable.stage_button_selected);
               //holder.getBinding().stageButton.setTextColor(Integer.parseInt("#FFFFFF"));
            }
        }
    }


    @Override
    public int getItemCount() {
        return totalPage.size();
    }

    public void updateStages(List<Integer> totalPage){
        this.totalPage = totalPage;
        notifyDataSetChanged();
    }

    public void updateButtonColor(int stage){
        this.stage = stage;
        notifyItemChanged(stage);
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
