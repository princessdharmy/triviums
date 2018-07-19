package com.app.horizon.screens.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.app.horizon.R;
import com.app.horizon.core.view.BaseActivity;
import com.app.horizon.databinding.ActivityMainBinding;
import com.app.horizon.screens.authentication.login.LoginActivity;

import javax.inject.Inject;


public class MainActivity extends BaseActivity {

    @Inject
    MainActivityViewModel viewModel;

    private ActivityMainBinding binding;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //mTextMessage.setText(R.string.title_home);
                        return true;
                    case R.id.navigation_profile:
                        //mTextMessage.setText(R.string.title_dashboard);
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
    }

    private void initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
