package com.app.horizon.screens.main;

import com.app.horizon.R;

import java.util.List;

import me.aartikov.alligator.Screen;
import me.aartikov.alligator.animations.AnimationData;
import me.aartikov.alligator.animations.SimpleTransitionAnimation;
import me.aartikov.alligator.animations.TransitionAnimation;
import me.aartikov.alligator.screenswitchers.FragmentScreenSwitcher;

public class DashBoardScreenSwitcherAnimationprovider implements FragmentScreenSwitcher.AnimationProvider {
    private List<Screen> mTabScreens;


    public DashBoardScreenSwitcherAnimationprovider(List<Screen> mTabScreens) {
        this.mTabScreens = mTabScreens;
    }

    @Override
    public TransitionAnimation getAnimation(Screen screenFrom, Screen screenTo, AnimationData animationData) {
        int indexFrom = mTabScreens.indexOf (screenFrom);
        int indexTo = mTabScreens.indexOf (screenTo);
        if (indexTo > indexFrom) {
            return new SimpleTransitionAnimation(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            return new SimpleTransitionAnimation(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

}
