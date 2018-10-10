package com.app.horizon.screens.main.home.stage;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.databinding.ActivityStageBinding;
import com.app.horizon.screens.main.home.stage.questions.QuestionFragment;
import com.app.horizon.screens.main.home.stage.stages.StagesAdapter;
import com.app.horizon.screens.main.home.stage.stages.StagesFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StageActivity extends BaseActivity<StageActivityViewModel> {

    private ActivityStageBinding binding;

    String categoryId, categoryName;

    @Inject
    public StageActivityViewModel viewModel;

    @Override
    public StageActivityViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();

        categoryId = getIntent().getExtras().getString("CategoryId");
        categoryName = getIntent().getExtras().getString("categoryName");

        showStageFragment();
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stage);
    }

    public void showStageFragment() {
        Fragment fragment = new StagesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putString("CategoryId", categoryId);
        args.putString("categoryName", categoryName);
        fragment.setArguments(args);
        transaction.replace(R.id.fragment_container, fragment)
                //This won't show a blank page when android back press button is clicked from fragment
                //.addToBackStack(null)
                .commit();
    }

}
