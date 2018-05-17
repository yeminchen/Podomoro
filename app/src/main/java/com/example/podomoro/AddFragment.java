package com.example.podomoro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cym on 3/9/18.
 */

public class AddFragment extends Fragment{

    private final String TAG = "AddFragment";

    EditText mTitle;
    EditText mContent;
    EditText mExpectedTime;

    Button mSubmit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        //read data from Edittext, and create an instance of task
        mTitle = (EditText) v.findViewById(R.id.title_add);
        String title = mTitle.getText().toString();

        mContent = (EditText) v.findViewById(R.id.content_add);
        String content = mContent.getText().toString();

        mExpectedTime = (EditText) v.findViewById(R.id.time_add);
        String time = mExpectedTime.getText().toString();


        mSubmit = (Button) v.findViewById(R.id.submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Try to figure out whether it's number
                String title = mTitle.getText().toString();
                String content = mContent.getText().toString();
                String time = mExpectedTime.getText().toString();
                if (isInteger(time)) {
                    int eTime = Integer.parseInt(time);
                    //insert to database
                    SingleTask newSingleTask = new SingleTask();
                    newSingleTask.setTitle(title);
                    newSingleTask.setContent(content);
                    newSingleTask.setRemain(eTime);
                    newSingleTask.setSolved(false);
                    TaskLab.get(getActivity()).addTask(newSingleTask);
                    Intent intent = new Intent(getActivity(), TaskListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Please enter time as an integer.", Toast.LENGTH_SHORT).show();
                    mExpectedTime.setText("");
                }
            }
        });




        return v;
    }

    /* a method used to check if String str can be converted to Integer*/
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
