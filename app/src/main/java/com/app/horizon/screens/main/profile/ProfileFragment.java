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
import com.app.horizon.screens.main.home.category.CategoryFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment<ProfileViewModel> {

    FragmentProfileBinding binding;
    AchievementAdapter adapter;
    List<DocumentSnapshot> achievements = new ArrayList<>();
    RecyclerView recyclerView;
    @Inject
    ViewModelProvider.Factory factory;
    private ProfileViewModel viewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public ProfileViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel.class);
        return viewModel;
    }

    public static ProfileFragment newInstance(){
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container,
                false);
        View view = binding.getRoot();

        initAchievementRecyclerview();
        showProfile();
        getAchievementData();
        return view;
    }

    public void showProfile(){
        UserProfile userProfile = viewModel.getUserDetails();
        binding.username.setText(userProfile.getName());

        Uri imageUri = Uri.parse(userProfile.getProfilePicture());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageUri)
                .setResizeOptions(new ResizeOptions(500, 500))
                .build();
        //binding.profilePix.setImageURI(imageUri);
        binding.profilePix.setController(
                Fresco.newDraweeControllerBuilder()
                .setOldController(binding.profilePix.getController())
                .setImageRequest(request)
                .build()
        );

    }

    public void initAchievementRecyclerview(){
        adapter = new AchievementAdapter(getActivity(), achievements);
        recyclerView = binding.achievements;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void getAchievementData(){
        viewModel.getLiveData().observe(getViewLifecycleOwner(), data -> {
            if(data !=null){
                Log.e("Data", String.valueOf(data));
                adapter.updateData(data);
            }
        });
    }

}
