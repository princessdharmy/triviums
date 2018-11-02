package com.app.horizon.screens.main.home.stage;

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

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.R;
import com.app.horizon.databinding.FragmentStagesBinding;
import com.app.horizon.screens.main.home.stage.questions.QuestionFragment;
import com.app.horizon.screens.main.home.stage.stages.StagesAdapter;
import com.app.horizon.utils.ConnectivityReceiver;
import com.app.horizon.utils.CountDownTimer;
import com.app.horizon.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import me.aartikov.alligator.Navigator;
import me.aartikov.alligator.annotations.RegisterScreen;


@RegisterScreen(StagesFragmentScreen.class)
public class newStageFragment extends Fragment {


    /**
     * A simple{@link Fragment } subclass.
     */


        FragmentStagesBinding binding;
        StagesAdapter adapter;
        RecyclerView recyclerView;
        List<Integer> totalPage = new ArrayList<>();
        String categoryId, categoryName, stageProgress;
        int currentScore;
        Button button;
        public CountDownTimer countDownTimer;

        public static final int MobileData = 2;
        public static final int WifiData = 1;
        boolean isConnected;
        //    @Inject
//        ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver(getActivity().getApplicationContext());
        //    @Inject
        Utils utils = new Utils(getContext());

//    @Inject
//    ViewModelProvider.Factory factory;
//    private StagesViewModel viewModel;

        private Navigator mNavigator = HorizonMainApplication.getNavigator();

        public newStageFragment() {
            // Required empty public constructor
        }


//    @Override
//    public StagesViewModel getViewModel() {
//        viewModel = ViewModelProviders.of(this, factory).get(StagesViewModel.class);
//        return viewModel;
//    }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stages, container,
                    false);
            View view = binding.getRoot();
            binding.setClick(new newStageFragment.MyHandler());

            StagesFragmentScreen screen = HorizonMainApplication.getScreenResolver().getScreen(this);
            //Get intent extras
            categoryId = screen.getCategoryId();
            categoryName = screen.getCategoryName();

            //Initialize the recyclerview
            initRecyclerView();

            //Clear the adapter to avoid duplicates
            totalPage.clear();

                try {

                        isConnected = true;
                        binding.noInternet.setVisibility(View.GONE);
                        binding.loader.setVisibility(View.VISIBLE);
                        binding.stageView.setVisibility(View.VISIBLE);
                        getProgress(categoryName);

                        //Call the showStage method
                        showStage(categoryId);

//                        isConnected = false;
//                        noInternet();

                } catch (Exception e){
                    utils.showSnackbar(getActivity(), "Error fetching data");
                }

            return view;
        }

        private void initRecyclerView() {
            adapter = new StagesAdapter(getActivity(), totalPage, listener);
            recyclerView = binding.stageView;
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }


        /**
         * Fetches stages of category
         *
         * @param categoryId
         */


        public void showStage(String categoryId) {
//        viewModel.getStage(categoryId).observe(getViewLifecycleOwner(), response -> {
            binding.loader.setVisibility(View.GONE);

            int page = 4;
            totalPage.clear();
            for (int i = 1; i <= page; i++) {
                totalPage.add(i);
            }
            adapter.updateStages(totalPage);

        }

        /**
         * Gets the progress of the user
         *
         * @param category
         */


        public void getProgress(String category) {


            int score = 1;
            getCurrentScore(score);

            int stageNumber = 1;
            getStageNumber(String.valueOf(stageNumber));
            adapter.updateButtonColor(stageNumber);
        }




        public String getStageNumber(String stageNum) {
            stageProgress = stageNum;
            return stageProgress;
        }

        public int getCurrentScore(int score) {
            currentScore = score;
            return currentScore;
        }


        public View.OnClickListener listener = view -> {

            //Check to confirm the instance of view i.e Button
            if (view instanceof Button) {
                button = (Button) view;

                //Checks to play stage 1 at default
                if (button.getText().toString().equals("1")) {
                    loadFragment();

                } else if (stageProgress == null) {
                    String message = String.valueOf(Integer.valueOf((String) button.getText()) - 1);
                    Log.e("Message", message);

                    Toast.makeText(getActivity(), "You must pass stage " +
                            message + " to continue ", Toast.LENGTH_SHORT).show();

                } else if (Integer.parseInt(String.valueOf(button.getText())) <= (Integer.parseInt(stageProgress) + 1)) {
                    loadFragment();
                } else {
                    String message = String.valueOf(Integer.valueOf((String) button.getText()) - 1);
                    Toast.makeText(getActivity(), "You must pass stage " +
                            message + " to continue ", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.e("View Instance:", "Error in getting the instance of view");
            }
        };

        public void loadFragment() {
            Fragment fragment = new QuestionFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Bundle args = new Bundle();
            args.putString("categoryId", categoryId);
            args.putString("categoryName", categoryName);
            args.putString("stageNumber", String.valueOf(button.getText()));
            args.putInt("currentScore", currentScore);
            args.putInt("totalPages", totalPage.size());
            fragment.setArguments(args);

            transaction.replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        private void noInternet() {
            binding.loader.setVisibility(View.GONE);
            binding.stageView.setVisibility(View.GONE);

            //show no internet status
            binding.noInternet.setVisibility(View.VISIBLE);
        }


        @Override
        public void onResume() {
            super.onResume();

            try {
                if (isConnected) {
                    binding.noInternet.setVisibility(View.GONE);
                    binding.loader.setVisibility(View.VISIBLE);
                    binding.stageView.setVisibility(View.VISIBLE);
                    //getActivity().getIntent();
                    getProgress(categoryName);
                    showStage(categoryId);
                } else {
                    noInternet();
                }
            } catch (Exception e){
                utils.showSnackbar(getActivity(), "Error fetching data");
            }
        }


        public class MyHandler {
            public void onButtonClick(View view) {
                getActivity().onBackPressed();
            }

        }

    }

