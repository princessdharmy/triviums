package com.app.horizon.screens.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.app.horizon.R;
import com.app.horizon.core.view.BaseActivity;
import com.app.horizon.databinding.ActivityMainBinding;
import com.app.horizon.screens.authentication.login.LoginActivity;
import com.app.horizon.screens.main.fragments.CategoryFragment;
import com.app.horizon.screens.main.fragments.ProfileFragment;
import com.app.horizon.utils.BottomNavigationBehaviour;


import javax.inject.Inject;


public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    MainActivityViewModel viewModel;

    private ActivityMainBinding binding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
                    case R.id.navigation_home:
                        fragment = new CategoryFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_profile:
                        fragment = new ProfileFragment();
                        //loadFragment(fragment);
                        return true;
                    case R.id.navigation_leader:
                        //mTextMessage.setText(R.string.title_notifications);
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);

        initBinding();

        //load the category fragment bu default
        loadFragment(new CategoryFragment());
    }

    private void initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Attaching bottom sheet behaviour - hide/show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)
                binding.navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehaviour());
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void loadFragment(Fragment fragment){
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
