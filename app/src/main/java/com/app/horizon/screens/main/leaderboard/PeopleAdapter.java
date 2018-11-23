package com.app.horizon.screens.main.leaderboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    Context context;


    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder {

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
