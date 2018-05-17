package com.example.podomoro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by cym on 3/13/18.
 */

public class TaskFragment extends Fragment {

    private TextView mTitle;
    private TextView mContent;
    private TextView mTime;
    private Button mStart;
    private String title;
    private String content;
    private UUID ID;
    private int remain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task, container, false);

        //get UUID, title, content, expected time from intent, then pass to podomoro. After task finished, podomoro search
        //for task by UUID and change expected time
        Intent intent = getActivity().getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        ID = UUID.fromString(intent.getStringExtra("ID"));
        remain = Integer.parseInt(intent.getStringExtra("remain"));

        mTitle = (TextView) v.findViewById(R.id.single_task_title);
        mTitle.append(title);
        mContent = (TextView) v.findViewById(R.id.single_task_content);
        mContent.append(content);
        mTime = (TextView) v.findViewById(R.id.single_task_time);
        mTime.append(remain+"");

        mStart = (Button) v.findViewById(R.id.start_button);
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), PodomoroActivity.class);
                intent1.putExtra("title", title);
                intent1.putExtra("ID", ID.toString());
                intent1.putExtra("content", content);
                intent1.putExtra("remain", remain+"");
                Account.score++;
                startActivity(intent1);
            }
        });

        return v;
    }
}
