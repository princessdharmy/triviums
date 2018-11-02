package com.app.horizon.screens.main.profile;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.core.network.models.UserProfile;
import com.app.horizon.databinding.FragmentProfileBinding;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.aartikov.alligator.annotations.RegisterScreen;

/**
 * A simple {@link Fragment} subclass.
 */

@RegisterScreen(ProfileScreen.class)
public class ProfileFragment extends BaseFragment<ProfileViewModel> {

    FragmentProfileBinding binding;
    AchievementAdapter adapter;
    List<DocumentSnapshot> achievements = new ArrayList<>();
    RecyclerView recyclerView;
//    @Inject
//    ViewModelProvider.Factory factory;
//    private ProfileViewModel viewModel;
    int totalScore = 0;
    ArrayList<Integer> values = new ArrayList<>();


    public ProfileFragment() {
        // Required empty public constructor
    }

//    @Override
//    public ProfileViewModel getViewModel() {
////        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
//        return null;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container,
                false);
        View view = binding.getRoot();

        initAchievementRecyclerview();
        getAchievementData();
        showProfile();

        return view;
    }

    public void showProfile(){
//        UserProfile userProfile = viewModel.getUserDetails();
//        binding.username.setText(userProfile.getName());

//        Uri imageUri = Uri.parse(userProfile.getProfilePicture());
//        binding.profilePix.setImageURI(imageUri);
    }

    public void initAchievementRecyclerview(){
        adapter = new AchievementAdapter(getActivity(), achievements);
        recyclerView = binding.achievements;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void getAchievementData(){
//        viewModel.getLiveData().observe(getViewLifecycleOwner(), data -> {
//            binding.progressBar.setVisibility(View.GONE);
//            binding.loadingTxt.setVisibility(View.GONE);
//            if(data !=null){
//                for(DocumentSnapshot document : data){
//                    values.add(Integer.valueOf(String.valueOf(document.getData().get("score"))));
//                }
//
//                for(Integer score : values) {
//                    totalScore += score;
//                }
//                if (totalScore > 0){
//                    //Check if user has played any quiz
//                    binding.message.setVisibility(View.GONE);
//                    binding.achievements.setVisibility(View.VISIBLE);
//                } else {
//                    //Check if user has played any quiz
//                    binding.message.setVisibility(View.VISIBLE);
//                    binding.achievements.setVisibility(View.GONE);
//                }
//                adapter.updateData(data);
//
//                if(totalScore <= 1){
//                    binding.totalXp.setText(String.format(getString(R.string.point), totalScore));
//                } else {
//                    binding.totalXp.setText(String.format(getString(R.string.points), totalScore));
//                }
//
//            }
//        });
    }

}
