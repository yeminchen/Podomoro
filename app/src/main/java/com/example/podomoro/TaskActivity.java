package com.example.podomoro;

import android.support.v4.app.Fragment;

public class TaskActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TaskFragment();
    }
}
