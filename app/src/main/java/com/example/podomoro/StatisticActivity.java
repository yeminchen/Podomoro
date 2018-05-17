package com.example.podomoro;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StatisticActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {return new StatisticFragment();}
}
