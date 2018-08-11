package com.app.horizon.screens.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.databinding.ActivityMainBinding;
import com.app.horizon.screens.authentication.login.LoginActivity;
import com.app.horizon.screens.main.home.category.CategoryFragment;
import com.app.horizon.screens.main.profile.ProfileFragment;
import com.app.horizon.utils.BottomNavigationBehaviour;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends BaseActivity<MainActivityViewModel> implements
        HasSupportFragmentInjector{

    private ActivityMainBinding binding;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    @Inject
    MainActivityViewModel viewModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
                    case R.id.navigation_home:
                        showCategoryFragment();
                        return true;
                    case R.id.navigation_profile:
                        showProfileFragment();
                        return true;
                    case R.id.navigation_leader:
                        //mTextMessage.setText(R.string.title_notifications);
                        return true;
                }
                return false;
            };


    @Override
    public MainActivityViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        //load the category fragment by default
        if (savedInstanceState == null) {
            showCategoryFragment();
        }
    }

    private void initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Attaching bottom sheet behaviour - hide/show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)
                binding.navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehaviour());
    }

    public void showCategoryFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.frame_container, CategoryFragment.newInstance())
                .commit();
    }

    private void showProfileFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.frame_container, ProfileFragment.newInstance())
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
