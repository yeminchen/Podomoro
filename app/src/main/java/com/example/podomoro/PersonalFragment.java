package com.example.podomoro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

/**
 * Created by cym on 2/21/18.
 */

public class PersonalFragment extends Fragment{

    private static final String TAG = "PersonalActivity";

    private Button mStatistic;
    private TextView mNum;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal, container, false);

        mNum = (TextView) v.findViewById(R.id.num);
        SharedPreferences pref = this.getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);

        int total = 0;
        Map score = pref.getAll();
        for (Object key : score.keySet()) {
            Object i = score.get(key);
            int time = Integer.parseInt(i.toString());
            total += time;
        }
        mNum.setText("Podomoro Finished: " + total +"");

        mStatistic = (Button) v.findViewById(R.id.statistic);
        mStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StatisticActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
}
