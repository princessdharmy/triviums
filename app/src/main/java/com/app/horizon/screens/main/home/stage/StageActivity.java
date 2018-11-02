package com.app.horizon.screens.main.home.stage;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.databinding.ActivityStageBinding;
import com.app.horizon.screens.main.home.StageActivityScreen;

import me.aartikov.alligator.Navigator;
import me.aartikov.alligator.annotations.RegisterScreen;

@RegisterScreen(StageActivityScreen.class)
public class StageActivity extends BaseActivity {

    private ActivityStageBinding binding;

    String categoryId, categoryName;

    private Navigator mNavigator  = HorizonMainApplication.getNavigator();



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

        mNavigator.goForward(new StagesFragmentScreen(categoryId, categoryName));

    }

}
