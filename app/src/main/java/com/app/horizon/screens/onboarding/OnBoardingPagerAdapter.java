package com.app.horizon.screens.onboarding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;

import javax.inject.Inject;

public class OnBoardingPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private int[] layouts;

    private OnBoardingPagerAdapter(){}

    @Inject
    public OnBoardingPagerAdapter(Context context) {
        this.context = context;
        layouts = new int[]{R.layout.slide1, R.layout.slide2, R.layout.slide3};
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(context);
        }

        View view = layoutInflater.inflate(layouts[position], container, false);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
