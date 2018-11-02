package com.app.horizon.screens.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.app.horizon.HorizonMainApplication;
import com.app.horizon.R;
import com.app.horizon.core.base.BaseActivity;
import com.app.horizon.databinding.ActivityMainBinding;
import com.app.horizon.screens.authentication.LoginActivity;
import com.app.horizon.screens.main.home.category.CategoryFragment;
import com.app.horizon.screens.main.leaderboard.LeaderboardFragment;
import com.app.horizon.screens.main.profile.ContainerProviderId;
import com.app.horizon.screens.main.profile.LeaderScreen;
import com.app.horizon.screens.main.profile.MainScreen;
import com.app.horizon.screens.main.profile.ProfileFragment;
import com.app.horizon.screens.main.profile.ProfileScreen;
import com.app.horizon.utils.BottomNavigationBehaviour;
import com.app.horizon.utils.ConnectivityReceiver;
import com.app.horizon.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import me.aartikov.alligator.NavigationContext;
import me.aartikov.alligator.NavigationContextBinder;
import me.aartikov.alligator.Navigator;
import me.aartikov.alligator.Screen;
import me.aartikov.alligator.annotations.RegisterScreen;
import me.aartikov.alligator.listeners.ScreenSwitchingListener;
import me.aartikov.alligator.screenswitchers.FragmentScreenSwitcher;


@RegisterScreen(MainScreen.class)
public class MainActivity extends BaseActivity<MainActivityViewModel> implements
        PopupMenu.OnMenuItemClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        ScreenSwitchingListener {

    private ActivityMainBinding binding;
    @Inject
    MainActivityViewModel viewModel;

    private Navigator mNavigator = HorizonMainApplication.getNavigator();
    private NavigationContextBinder mNavigationContextBinder = HorizonMainApplication.getNavigationContextBinder();
    private FragmentScreenSwitcher mScreenSwitcher;

    private Map<Integer, Screen> mTabScreenMap = new LinkedHashMap<>();

    private Screen getTabScreen(int tabId) {
        return  mTabScreenMap.get(tabId);
    }

    private void initTabScreenMap() {
        mTabScreenMap.put(R.id.navigation_home, new HomeScreen("home"));
        mTabScreenMap.put(R.id.navigation_profile, new ProfileScreen("profile"));
        mTabScreenMap.put(R.id.navigation_leader, new LeaderScreen("Leader"));

    }
//
//    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = item -> {
//        Fragment fragment;
//        switch (item.getItemId()) {
//            case R.id.navigation_home:
//                binding.title.setText(R.string.categories);
//                fragment = new CategoryFragment();
//                loadFragment(fragment);
//                return true;
//            case R.id.navigation_profile:
//                binding.title.setText(R.string.profile);
//                fragment = new ProfileFragment();
//                loadFragment(fragment);
//                return true;
//            case R.id.navigation_leader:
//                binding.title.setText(R.string.people);
//                fragment = new LeaderboardFragment();
//                loadFragment(fragment);
//                return true;
//        }
//        return false;
//    };


//    @Override
//    public MainActivityViewModel getViewModel() {
//        return viewModel;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();
        initTabScreenMap();
        setupBottomNavigation();
        if (savedInstanceState == null) {
            mNavigator.switchTo(getTabScreen(R.id.navigation_home));
        }

        //load the category fragment by default
        binding.title.setText(R.string.categories);
//        loadFragment(new CategoryFragment());

        binding.menuBtn.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
            popupMenu.setOnMenuItemClickListener(MainActivity.this);
            popupMenu.inflate(R.menu.menu);
            popupMenu.show();
        });
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //Attaching bottom sheet behaviour - hide/show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)
                binding.navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehaviour());
    }

    public void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    //This won't show a blank page when android back press button is clicked from fragment
                    //.addToBackStack(null)
                    .replace(R.id.frame_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_logout:
                goLoginScreen();
                return true;
        }
        return false;
    }

    private void goLoginScreen() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




    private void setupBottomNavigation() {
        binding.navigation.setOnNavigationItemSelectedListener(this);
        mScreenSwitcher = new FragmentScreenSwitcher(HorizonMainApplication.getNavigationFactory(), getSupportFragmentManager(),
                R.id.frame_container, new DashBoardScreenSwitcherAnimationprovider(getTabScreens()));
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
//        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }

    private List<Screen> getTabScreens() {
        return new ArrayList<>(mTabScreenMap.values());
    }


    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        bindNavigationContext();
    }

    private void bindNavigationContext() {
        NavigationContext.Builder builder = new NavigationContext.Builder(this)
                .screenSwitcher(mScreenSwitcher)
                .screenSwitchingListener(this)
                .transitionAnimationProvider(new DashboardTransitionAnimationProvider());

        Fragment fragment = mScreenSwitcher.getCurrentFragment();
        if (fragment != null && fragment instanceof ContainerProviderId) {
            builder.containerId(((ContainerProviderId) fragment).getContainerId())
                    .fragmentManager(fragment.getChildFragmentManager());       // Use child fragment manager for nested navigation
        }

        try {

            mNavigationContextBinder.bind(builder.build());
        }catch (Exception e){
            Log.e("Horizon_", "Failed to bind");

        }

    }

    private int getTabId(Screen tabScreen) {
        for (Map.Entry<Integer, Screen> entry : mTabScreenMap.entrySet()) {
            if (tabScreen.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Screen screen = getTabScreen(menuItem.getItemId());
        mNavigator.switchTo(screen);
        return false;
    }

    @Override
    public void onScreenSwitched(@Nullable Screen screenFrom, Screen screenTo) {
        int tabId = getTabId(screenTo);
        binding.navigation.getMenu().findItem(tabId).setChecked(true);
        bindNavigationContext();  // rebind NavigationContext because we need to set another container id and another child fragment manager.


    }
}
