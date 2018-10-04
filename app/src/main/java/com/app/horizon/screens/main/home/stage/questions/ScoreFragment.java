package com.app.horizon.screens.main.home.stage.questions;


import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.horizon.R;
import com.app.horizon.databinding.FragmentScoreBinding;
import com.app.horizon.screens.main.home.stage.stages.StagesFragment;
import com.app.horizon.utils.CountDownTimer;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link DialogFragment} subclass.
 */
public class ScoreFragment extends DialogFragment {

    FragmentScoreBinding binding;
    DialogFragment dialogFragment;
    CountDownTimer countDownTimer;
    int score;

    public ScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);

        //Receive the bundle passed
        Bundle bundle = getArguments();
        score = bundle.getInt("score");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container,
                false);
        View view = binding.getRoot();

        showScore();
        return view;
    }


    public void showScore(){
        if(score >= 2){
            binding.playerName.setText(R.string.score_pass );

            binding.congratsMsg.setText(R.string.pass_message);

            binding.scoreTxt.setText(String.valueOf(score));

        } else {
            binding.playerName.setText(R.string.score_fail );

            binding.congratsMsg.setText(R.string.fail_message);

            binding.scoreTxt.setText(String.valueOf(score));
        }

        countDownTimer = new CountDownTimer(5L, TimeUnit.SECONDS) {
            @Override
            public void onTick(long tickValue) {

            }

            @Override
            public void onFinish() {
                dismissDialogFragment();
            }
        };
        countDownTimer.start();

    }

    /**
     * This removes all back stack states with name dialog from top until bottom of
     * stack is reached or a back stack state entry with different name is reached.
     * No need to explicitly call dismiss on dialog.
     */
    public void dismissDialogFragment(){
        getActivity().getSupportFragmentManager().popBackStack("dialog",
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
