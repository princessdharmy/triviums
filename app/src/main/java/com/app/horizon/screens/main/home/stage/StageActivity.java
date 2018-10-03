package com.app.horizon.screens.main.home.stage;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.databinding.ActivityStageBinding;
import com.app.horizon.screens.main.home.stage.stages.StagesFragment;

import javax.inject.Inject;

public class StageActivity extends BaseActivity<StageActivityViewModel> {

    private ActivityStageBinding binding;
    String value;
    String categoryName;
    @Inject
    StageActivityViewModel viewModel;

    @Override
    public StageActivityViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        value = getIntent().getExtras().getString("CategoryId");
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
        args.putString("CategoryId", value);
        args.putString("categoryName", categoryName);
        fragment.setArguments(args);
        transaction.replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

}
