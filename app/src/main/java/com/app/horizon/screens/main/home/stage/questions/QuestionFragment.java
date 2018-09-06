package com.app.horizon.screens.main.home.stage.questions;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.core.store.online.question.Question;
import com.app.horizon.databinding.FragmentQuestionBinding;
import com.app.horizon.utils.CountDownTimer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends BaseFragment<QuestionViewModel> {

    FragmentQuestionBinding binding;
    @Inject
    ViewModelProvider.Factory factory;
    public QuestionViewModel viewModel;
    public CountDownTimer countDownTimer;

    public List<Question> questionList = new ArrayList<>();
    public List<Question> randomPicks = new ArrayList<>();
    public String categoryId = "10";
    public String page = "1";
    public int position = 0;
    public int totalQuestion = 3;
    public int count = 0;
    public int score;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public QuestionViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(QuestionViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container,
                false);
        View view = binding.getRoot();
        binding.setClick(new MyHandler());

        getQuestion(categoryId, page);
        return view;
    }

    /**
     * Fetches question from the repository
     *
     * @param categoryId
     * @param page
     */
    public void getQuestion(String categoryId, String page) {
        viewModel.getQuestion(categoryId, page).observe(getViewLifecycleOwner(), response -> {
            if (response != null) {
                questionList.clear();
                questionList.addAll(response.getData());
            }
            generateRandomQuestions(questionList, totalQuestion);
            displayQuestion(position);
        });
    }

    /**
     * Generate shuffled question list in an array
     *
     * @param list
     * @param n
     * @return
     */
    private void generateRandomQuestions(List<Question> list, int n) {
        List<Question> copy = new LinkedList<>(list);
        Collections.shuffle(copy);
        randomPicks = copy.subList(0, n);
    }

    /**
     * Displays question
     */
    public void displayQuestion(int position) {
        if (position <= (randomPicks.size() - 1)) {
            int currentQuestion = position + 1;
            binding.currentQuestion.setText(String.valueOf(currentQuestion));
            binding.totalQuestions.setText(String.valueOf(totalQuestion));
            binding.setQuestion(randomPicks.get(position));
            questionTimer();
        } else {
            showScore();
        }

    }

    /**
     * This gets the button(option) value and compares with the correct answer.
     *
     * @param buttonText
     * @param position
     */
    private void calculateScore(String buttonText, int position) {
        if (buttonText.equals(randomPicks.get(position).getCorrectAnswer())) {
            if(score == 0){
                score = count + 1;
            } else {
                score = score + 1;
            }
        }
    }

    /**
     * This displays the countdown timer per question
     */
    public void questionTimer() {
        countDownTimer = new CountDownTimer(10L, TimeUnit.SECONDS) {
            @Override
            public void onTick(long tickValue) {
                //Set timer tick value on progressbar text view
                binding.timer.setText(String.valueOf(tickValue));

                //Since the progressbar moves in opposite direction with the tick value, e.g
                // tickValue[10,9,8,7,...], progressBar[10,9,8,7,...], a little
                //calculation is done to make the progressbar move as expected. e.g
                //tickValue[10,9,8,7,...], progressBar[1,2,3,4,...]
                long progress = (10 - tickValue) + 1; //10 is the start value

                //Set the progress of the progressbar alongside with the timer tick value
                binding.progressBar2.setProgress((int) progress);
            }

            @Override
            public void onFinish() {
                position++;
                countDownTimer.cancel();
                displayQuestion(position);
            }
        };
        countDownTimer.start();
    }


    /**
     * This method shows the quiz score by calling a DialogFragment
     */
    private void showScore() {

        DialogFragment dialogFragment = new ScoreFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack("dialog");

        //Pass the score value via bundle
        Bundle args = new Bundle();
        args.putInt("score", score);
        Log.e("Bundle Score", String.valueOf(score));
        dialogFragment.setArguments(args);

        dialogFragment.show(ft, "dialog");
    }

    /**
     * Handles the button click
     */
    public class MyHandler {
        public void onButtonClick(View view) {
            getActivity().onBackPressed();
        }

        //Checks for the clicked/selected button
        public void onOptionClick(View view) {
            switch (view.getId()) {
                case R.id.option1:
                    calculateScore(String.valueOf(binding.option1.getText()), position);
                    position++;
                    countDownTimer.cancel();
                    displayQuestion(position);
                    break;
                case R.id.option2:
                    calculateScore(String.valueOf(binding.option2.getText()), position);
                    position++;
                    countDownTimer.cancel();
                    displayQuestion(position);
                    break;
                case R.id.option3:
                    calculateScore(String.valueOf(binding.option3.getText()), position);
                    position++;
                    countDownTimer.cancel();
                    displayQuestion(position);
                    break;
                case R.id.option4:
                    calculateScore(String.valueOf(binding.option4.getText()), position);
                    position++;
                    countDownTimer.cancel();
                    displayQuestion(position);
                    break;
                default:
                    throw new RuntimeException("Unknow button ID");
            }
        }
    }

    @Override
    public void onDestroyView() {
        countDownTimer.cancel();
        super.onDestroyView();
    }
}
