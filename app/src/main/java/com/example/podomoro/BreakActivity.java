package com.example.podomoro;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class BreakActivity extends AppCompatActivity {

    int remain;
    String title, content;
    UUID id;
    SingleTask mSingleTask;


    private static final String TAG = "BreakActivity";

    /*
    @Override
    protected Fragment createFragment() {
        return new PodomoroFragment();
    } */


    private int recLen = 6;
    private TextView txtView;
    Timer timer = new Timer();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent msg = getIntent();
        remain = Integer.parseInt(msg.getStringExtra("remain"));
        id = UUID.fromString(msg.getStringExtra("ID"));
        title = msg.getStringExtra("title");
        content = msg.getStringExtra("content");



        setContentView(R.layout.activity_break);
        txtView = (TextView)findViewById(R.id.btime);

        timer.schedule(task, 1000, 1000);       // timeTask

        Button mQuit = (Button) findViewById(R.id.b_quit);
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BreakActivity.this, TaskListActivity.class);
                timer.cancel();
                startActivity(intent);
            }
        });
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    recLen--;
                    String time = convertFormat(recLen);
                    txtView.setText(""+time);
                    if(recLen < 0){
                        timer.cancel();
                        txtView.setVisibility(View.GONE);
                        Intent intent = new Intent(getApplicationContext(),PodomoroActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("ID", id.toString());
                        intent.putExtra("content", content);
                        intent.putExtra("remain", remain+"");
                        startActivity(intent);
                    }
                }
            });
        }
    };

    public String convertFormat(int toConvert) {
        int mins = toConvert / 60;
        int secs = toConvert % 60;
        String min = "";
        String sec = "";
        if (mins < 10) {
            min = "0" + mins;
        } else {
            min = mins+"";
        }
        if (secs < 10) {
            sec = "0" + secs;
        } else {
            sec = secs + "";
        }
        return min + ":" + sec;
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
