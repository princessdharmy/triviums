package com.app.horizon.screens.main.home.stage.stages;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.databinding.FragmentStagesBinding;
import com.app.horizon.screens.main.home.stage.questions.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class StagesFragment extends BaseFragment<StagesViewModel>{

    FragmentStagesBinding binding;
    StagesFragmentAdapter adapter;
    RecyclerView recyclerView;
    List<Integer> totalPage = new ArrayList<>();
    String categoryId, categoryName, stageProgress;
    int currentScore;
    Button button;

    @Inject
    ViewModelProvider.Factory factory;
    private StagesViewModel viewModel;

    public StagesFragment() {
        // Required empty public constructor
    }

    public static StagesFragment newInstance(){
        StagesFragment fragment = new StagesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public StagesViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(StagesViewModel.class);
        return viewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stages, container,
                false);
        View view = binding.getRoot();
        binding.setClick(new MyHandler());

        //Get intent extras
        categoryId = getArguments().getString("CategoryId");
        categoryName = getArguments().getString("categoryName");

        //Clear the adapter to avoid duplicates
        totalPage.clear();

        //Initialize the recyclerview
        initRecyclerView();

        getProgress(categoryName);

        //Call the showStage method
        showStage(categoryId);

        return view;
    }

    private void initRecyclerView(){
        adapter = new StagesFragmentAdapter(getActivity(), totalPage, listener);
        recyclerView = binding.stageView;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    /**
     * Fetches stages of category
     */
    public void showStage(String categoryId){
        viewModel.getStage(categoryId).observe(getViewLifecycleOwner(), response -> {
            binding.progressBar.setVisibility(View.GONE);
            binding.loadingTxt.setVisibility(View.GONE);

            int page = response.getPaging().getTotalPages().intValue();
            for(int i = 1; i <= page; i++){
                totalPage.add(i);
            }
            adapter.updateStages(totalPage);
        });
    }

    /**
     * Gets the progress of the user
     * @param category
     */
    public void getProgress(String category){
        viewModel.getProgressDetails(category).observe(getViewLifecycleOwner(), data -> {
            if(data != null){
                Log.e("Score", data.get("score").getClass().getSimpleName());
                //currentScore = data.get("score");

                Log.e("Stage level", String.valueOf(data.get("stageNumber")));
                int stageNumber = Integer.parseInt(data.get("stageNumber").toString());
                Log.e("Stage Number", String.valueOf(stageNumber));
                getStageNumber(String.valueOf(stageNumber));
                adapter.updateButtonColor(stageNumber);
            }
        });
    }

    public String getStageNumber(String stageNum){
        stageProgress = stageNum;
        return stageProgress;
    }


    public View.OnClickListener listener = view -> {
        Log.e("Score", String.valueOf(currentScore));

        //Check to confirm the instance of view i.e Button
        if(view instanceof Button){
            button = (Button) view;

            if(button.getText() == "1"){
                Fragment fragment = new QuestionFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Bundle args = new Bundle();
                args.putString("categoryId", categoryId);
                args.putString("categoryName", categoryName);
                args.putString("stageNumber", String.valueOf(button.getText()));
                fragment.setArguments(args);

                transaction.replace(R.id.fragment_container, fragment)
                        .addToBackStack("dialog")
                        .commit();

            } else if(stageProgress == button.getText()){
                Fragment fragment = new QuestionFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                Bundle args = new Bundle();
                args.putString("categoryId", categoryId);
                args.putString("categoryName", categoryName);
                args.putString("stageNumber", String.valueOf(button.getText()));
                fragment.setArguments(args);

                transaction.replace(R.id.fragment_container, fragment)
                        .addToBackStack("dialog")
                        .commit();
            }
            else {
                String message = String.valueOf(Integer.valueOf((String) button.getText()) - 1);
                Toast.makeText(getActivity(), "You must pass stage " +
                        message + " to continue ", Toast.LENGTH_SHORT).show();
            }

        } else {
            Log.e("View Instance:", "Error in getting the instance of view");
        }


    };

    public class MyHandler{
        public void onButtonClick(View view) {
            getActivity().finish();
        }
    }




}
