package com.example.podomoro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class PodomoroActivity extends AppCompatActivity {

    private int remain;
    private String title, content;
    private UUID id;
    private SingleTask mSingleTask;



    private static final String TIME_INDEX = "t_index";
    private static final String BREAK_INDEX = "b_index";
    private static final String TAG = "PodomoroActivity";

    /*
    @Override
    protected Fragment createFragment() {
        return new PodomoroFragment();
    } */


    private int recLen = 11;
    private boolean isBreak = false;
    private TextView intro;
    private TextView txtView;
    Timer timer = new Timer();

    @Override
    public void onSaveInstanceState(Bundle savedInstanceStats){
        super.onSaveInstanceState(savedInstanceStats);
        savedInstanceStats.putInt(TIME_INDEX, recLen);
        savedInstanceStats.putBoolean(BREAK_INDEX, isBreak);
        savedInstanceStats.putString("title", title);
        savedInstanceStats.putString("content", content);
        savedInstanceStats.putInt("remain", remain);
        savedInstanceStats.putString("ID", id.toString());
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent msg = getIntent();
        remain = Integer.parseInt(msg.getStringExtra("remain"));
        id = UUID.fromString(msg.getStringExtra("ID"));
        title = msg.getStringExtra("title");
        content = msg.getStringExtra("content");


        setContentView(R.layout.fragment_podomoro);

        if (savedInstanceState != null) {
            recLen = savedInstanceState.getInt(TIME_INDEX, 11);
            isBreak = savedInstanceState.getBoolean(BREAK_INDEX, false);
            remain = savedInstanceState.getInt("remain");
            id = UUID.fromString(savedInstanceState.getString("ID"));
            title = savedInstanceState.getString("title");
            content = savedInstanceState.getString("content");
        }

        mSingleTask = new SingleTask(id);
        mSingleTask.setSolved(false);
        mSingleTask.setContent(content);
        mSingleTask.setTitle(title);
        mSingleTask.setRemain(remain);
        Log.e("SingleTask", mSingleTask.getTitle()+"");
        Log.e("SingleTask", mSingleTask.getId()+"");
        Log.e("SingleTask", mSingleTask.getRemain()+"");

        txtView = (TextView)findViewById(R.id.txttime);
        intro = (TextView)findViewById(R.id.intro);

        if (!isBreak) {
            intro.setText(R.string.p_clock);
        }else {
            intro.setText(R.string.b_clock);
        }

        timer.schedule(task, 1000, 1000);// timeTask

        Button mQuit = (Button) findViewById(R.id.p_quit);
        mQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PodomoroActivity.this, TaskListActivity.class);
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
                        String minsec = convertFormat(recLen);
                        txtView.setText(minsec);
                        if(recLen <= 0) {
                            //timer.cancel();
                            //txtView.setVisibility(View.GONE);
                            if (isBreak) {
                                recLen = 11;
                                isBreak = false;
                                intro.setText(R.string.p_clock);
                            } else {
                                recLen = 6;
                                isBreak = true;
                                intro.setText(R.string.b_clock);
                            }
                            //use for statistic
                            if (isBreak) {
                                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                                int oldValue = pref.getInt(title, 0);
                                Log.d("oldvalue", oldValue + "");
                                int newValue = oldValue + 1;
                                Log.d("PS", newValue + "");
                                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                                editor.putInt(title, newValue);
                                editor.apply();
                                remain = remain - 1;
                                mSingleTask.setRemain(remain);
                            }
                            if (remain == 0) {
                                //task finished, no need to break
                                mSingleTask.setSolved(true);
                                TaskLab taskLab = TaskLab.get(getApplicationContext());
                                taskLab.updateTask(mSingleTask);
                                Toast.makeText(getApplicationContext(), "SingleTask Finished", Toast.LENGTH_SHORT).show();
                                Intent finish = new Intent(getApplicationContext(), TaskListActivity.class);
                                startActivity(finish);
                                return;
                            }
                            TaskLab taskLab = TaskLab.get(getApplicationContext());
                            taskLab.updateTask(mSingleTask);
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
        task.cancel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    public int getRemain(){
        return remain;
    }
}
