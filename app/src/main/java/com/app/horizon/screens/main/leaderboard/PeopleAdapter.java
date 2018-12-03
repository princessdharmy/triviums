package com.app.horizon.screens.main.leaderboard;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.databinding.PeopleListBinding;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

import javax.inject.Inject;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder> {

    private Context context;
    List<DocumentSnapshot> peopleList;
    Uri imageUri;

    @Inject
    public PeopleAdapter(Context context, List<DocumentSnapshot> peopleList) {
        this.context = context;
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public PeopleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.people_list,
                viewGroup, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeopleViewHolder holder, int position) {
        String userName = String.valueOf(peopleList.get(position).getData().get("name"));
        String image = String.valueOf(peopleList.get(position).getData().get("profilePicture"));

        //Display the default Photo
        imageUri = Uri.parse(image);
        holder.getBinding().serverUser.setImageURI(imageUri);

        //Display the username
        holder.getBinding().serverUserName.setText(userName);
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public void updateData(List<DocumentSnapshot> value){
        this.peopleList = value;
        notifyDataSetChanged();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder {

        PeopleListBinding binding;

        public PeopleViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setTag(getBinding());
        }
        public PeopleListBinding getBinding() {
            return binding;
        }
    }
}
