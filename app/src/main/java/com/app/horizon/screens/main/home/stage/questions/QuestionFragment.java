package com.app.horizon.screens.main.home.stage.questions;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.horizon.R;
import com.app.horizon.core.base.BaseFragment;
import com.app.horizon.databinding.FragmentQuestionBinding;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends BaseFragment<QuestionViewModel> {

    FragmentQuestionBinding binding;
    @Inject
    ViewModelProvider.Factory factory;
    public QuestionViewModel viewModel;

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

        return view;
    }

    public class MyHandler{
        public void onButtonClick(View view) {
            getActivity().onBackPressed();
        }
    }

}
