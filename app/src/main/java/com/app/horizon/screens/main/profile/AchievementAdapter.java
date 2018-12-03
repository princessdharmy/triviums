package com.app.horizon.screens.main.profile;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.databinding.AchievementsBinding;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {

    private Context context;
    List<DocumentSnapshot> achievements;


    @Inject
    public AchievementAdapter(Context context, List<DocumentSnapshot> achievements) {
        this.context = context;
        this.achievements = achievements;
    }

    @NonNull
    @Override
    public AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.achievements,
                viewGroup, false);
        return new AchievementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewHolder holder, int position) {

        String categoryTitle = String.valueOf(achievements.get(position).getId());
        String stageNumber = String.valueOf(achievements.get(position).getData().get("stageNumber"));
        String totalPages = String.valueOf(achievements.get(position).getData().get("totalPages"));

        holder.getBinding().categoryTitle.setText(categoryTitle);
        holder.getBinding().stage.setText(stageNumber);
        holder.getBinding().totalStages.setText(totalPages);

        holder.getBinding().linearProgress.setMax(Integer.parseInt(totalPages));
        holder.getBinding().linearProgress.setProgress(Integer.parseInt(stageNumber));

    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public void updateData(List<DocumentSnapshot> value){
        this.achievements = value;
        notifyDataSetChanged();
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder {

        AchievementsBinding binding;

        public AchievementViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setTag(getBinding());
        }

        public AchievementsBinding getBinding() {
            return binding;
        }
    }
}
