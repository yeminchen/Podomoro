package com.example.podomoro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cym on 1/17/18.
 */

public class TaskListFragment extends Fragment {

    private static final String TAG = "TaskListFragment";

    private RecyclerView mTaskRecyclerView;
    private TaskAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_list, container, false);


        mTaskRecyclerView = (RecyclerView) v.findViewById(R.id.task_recycler_view);
        mTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return v;
    }

    private void updateUI() {
        TaskLab taskLab = TaskLab.get(getActivity());
        List<SingleTask> singleTasks = taskLab.getTasks();

        mAdapter = new TaskAdapter(singleTasks);
        mTaskRecyclerView.setAdapter(mAdapter);
    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private SingleTask mSingleTask;

        private TextView mTitleTextView;
        private TextView mRemainTextView;

        public TaskHolder (LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_task, parent, false));

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.task_title);
            mRemainTextView = (TextView) itemView.findViewById(R.id.task_remain);
        }

        public void bind(SingleTask singleTask) {
            mSingleTask = singleTask;
            mTitleTextView.setText(mSingleTask.getTitle());
            mRemainTextView.setText(mSingleTask.getRemain()+"");

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), TaskActivity.class);
            intent.putExtra("title", mSingleTask.getTitle());
            intent.putExtra("ID", mSingleTask.getId().toString());
            intent.putExtra("content", mSingleTask.getContent());
            intent.putExtra("remain", mSingleTask.getRemain()+"");
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<SingleTask> mSingleTasks;

        public TaskAdapter(List<SingleTask> singleTasks) {
            mSingleTasks = singleTasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new TaskHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position) {
            SingleTask singleTask = mSingleTasks.get(position);
            holder.bind(singleTask);
        }

        @Override
        public int getItemCount() {
            return mSingleTasks.size();
        }

        public void setCrimes(List<SingleTask> crimes) {
            mSingleTasks = crimes;
        }
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
