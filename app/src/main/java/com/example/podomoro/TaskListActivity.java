package com.example.podomoro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TaskListActivity extends AppCompatActivity {

    private static final String TAG = "TaskListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragments);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment_one = fm.findFragmentById(R.id.fragment_one);
        Fragment fragment_two = fm.findFragmentById(R.id.fragment_two);


        if (fragment_one == null) {
            fragment_one = new TaskListFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_one, fragment_one)
                    .commit();
        }

        if (fragment_two == null) {
            fragment_two = new ButtonFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_two, fragment_two)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
