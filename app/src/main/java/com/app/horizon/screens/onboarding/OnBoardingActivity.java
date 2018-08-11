package com.app.horizon.screens.onboarding;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.databinding.ActivityOnBoardingBinding;
import com.app.horizon.screens.authentication.login.LoginActivity;
import com.app.horizon.screens.authentication.login.LoginActivityViewModel;

import javax.inject.Inject;

import static dagger.android.AndroidInjection.inject;


public class OnBoardingActivity extends BaseActivity<OnBoardingViewModel> {

    private ActivityOnBoardingBinding binding;

    @Inject
    OnBoardingPagerAdapter adapter;

    OnBoardingViewModel viewModel;
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public OnBoardingViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(OnBoardingViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);

        initBinding();

        //Making notification bar transparent
        changeStatusBarColor();
    }

    private void initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding);
        binding.setClickHandler(new OnBoardingActivityClickHandler());
        binding.setViewModel(viewModel);
        viewModel.setIsLastPage(false);
        setupViewPager();
    }

    private void setupViewPager(){
        binding.container.setAdapter(adapter);
        binding.container.addOnPageChangeListener(new PageChangeListener());
        enableIndicator(0);
    }

    private void enableIndicator(int position){
        disableAllIndicator();
        switch (position){
            case 0:
                binding.indicatorOne.setSelected(true);
                break;
            case 1:
                binding.indicatorTwo.setSelected(true);
                break;
            case 2:
                binding.indicatorThree.setSelected(true);
                break;
        }
    }

    private void disableAllIndicator(){
        binding.indicatorOne.setSelected(false);
        binding.indicatorTwo.setSelected(false);
        binding.indicatorThree.setSelected(false);
    }

    private void launchHomeScreen() {
        viewModel.setFirstTimeLaunch();
        startActivity(new Intent(OnBoardingActivity.this, LoginActivity.class));
        finish();
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#3C4258"));
        }
    }

    public class OnBoardingActivityClickHandler{
        public void onNextClicked(View view){
            int currentItemPosition = binding.container.getCurrentItem();
            binding.container.setCurrentItem(currentItemPosition + 1);
        }

        public void onSkipClicked(View view){
            launchHomeScreen();
        }
    }

    public class PageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            enableIndicator(position);
            if (position == adapter.getCount() - 1){
                viewModel.setIsLastPage(true);
            } else {
                viewModel.setIsLastPage(false);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
