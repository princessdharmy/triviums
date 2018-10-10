package com.app.horizon.utils;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.horizon.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Utils {

    public Context context;

    @Inject
    public Utils(Context context) {
        this.context = context;
    }

    public void showSnackbar(Activity context, String message){
        Snackbar snackbar = Snackbar.make(context.findViewById(android.R.id.content), message,
                Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.gravity = Gravity.TOP;
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(Color.WHITE);

        TextView textView = (view).findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.navIconColor));
        snackbar.show();
    }


}
