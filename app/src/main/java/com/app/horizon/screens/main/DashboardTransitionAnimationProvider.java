package com.app.horizon.screens.main;

import android.support.annotation.Nullable;

import com.app.horizon.R;

import me.aartikov.alligator.Screen;
import me.aartikov.alligator.TransitionType;
import me.aartikov.alligator.animations.AnimationData;
import me.aartikov.alligator.animations.SimpleTransitionAnimation;
import me.aartikov.alligator.animations.TransitionAnimation;
import me.aartikov.alligator.animations.providers.TransitionAnimationProvider;

public class DashboardTransitionAnimationProvider implements TransitionAnimationProvider {
    @Override
    public TransitionAnimation getAnimation(TransitionType transitionType, Class<? extends Screen> screenClassFrom, Class<? extends Screen> screenClassTo, boolean isActivity, @Nullable AnimationData animationData) {
        if (isActivity) {
            return TransitionAnimation.DEFAULT;
        } else {
            return new SimpleTransitionAnimation(R.anim.stay, R.anim.fade_out);
        }
    }}