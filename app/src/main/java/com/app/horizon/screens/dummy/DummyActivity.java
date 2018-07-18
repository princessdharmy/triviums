package com.app.horizon.screens.dummy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.bottomappbar.BottomAppBar;

import com.app.horizon.R;
import com.app.horizon.core.view.BaseActivity;
import com.app.horizon.databinding.MaterialTestBinding;

/**
 * Created by Ayokunle Paul on 7/4/18.
 */
public class DummyActivity extends BaseActivity {

    private MaterialTestBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.material_test);

        binding.bottomBar.setNavigationOnClickListener(view -> {
            if (binding.bottomBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_END){
                binding.bottomBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            } else {
                binding.bottomBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            }
        });
    }
}
