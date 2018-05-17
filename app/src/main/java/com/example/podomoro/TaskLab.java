package com.example.podomoro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.podomoro.database.TaskBaseHelper;
import com.example.podomoro.database.TaskCursorWrapper;
import com.example.podomoro.database.TaskDbSchema.TaskTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by cym on 1/24/18.
 */

public class TaskLab {
/*
    private static TaskLab sTaskLabLab;

    private List<SingleTask> mTasks;

    public static TaskLab get(Context context) {
        if (sTaskLabLab == null) {
            sTaskLabLab = new TaskLab(context);
        }

        return sTaskLabLab;
    }

    private TaskLab(Context context) {
        mTasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SingleTask task = new SingleTask();
            task.setTitle("Crime #" + i);
            task.setSolved(i % 2 == 0);
            mTasks.add(task);
        }
    }

    public List<SingleTask> getTasks() {
        return mTasks;
    }

    public SingleTask getTask(UUID id) {
        for (SingleTask task : mTasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }

        return null;
    }*/

    private static TaskLab sTaskLab;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public TaskLab() {

    }

    public static TaskLab get(Context context) {
        if (sTaskLab == null) {
            sTaskLab = new TaskLab(context);
        }
        return sTaskLab;
    }

    private TaskLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();

    }

    public void addTask(SingleTask t) {
        ContentValues values = getContentValues(t);

        mDatabase.insert(TaskTable.NAME, null, values);
    }

    public List<SingleTask> getTasks() {
        List<SingleTask> singleTasks = new ArrayList<>();

        String[] zero = new String[] {String.valueOf(0)};
        TaskCursorWrapper cursor = queryTasks(
                "remain > 0", null);
        //null,null);

        //TaskCursorWrapper cursor = new TaskCursorWrapper(mDatabase.rawQuery("SELECT * FROM tasks WHERE remain > 0",
        //        null));

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                singleTasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return singleTasks;
    }

    public SingleTask getTask(UUID id) {

        TaskCursorWrapper cursor = queryTasks(
                TaskTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTask();
        } finally {
            cursor.close();
        }
    }

    public void updateTask(SingleTask singleTask) {
        String uuidString = singleTask.getId().toString();
        ContentValues values = getContentValues(singleTask);

        mDatabase.update(TaskTable.NAME, values,
                TaskTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TaskTable.NAME,
                null, // columns - null selects all columns
                 whereClause,
                 whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new TaskCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(SingleTask singleTask){
        ContentValues values = new ContentValues();
        values.put(TaskTable.Cols.UUID, singleTask.getId().toString());
        values.put(TaskTable.Cols.TITLE, singleTask.getTitle());
        values.put(TaskTable.Cols.CONTENT, singleTask.getContent());
        values.put(TaskTable.Cols.REMAIN, singleTask.getRemain());
        values.put(TaskTable.Cols.SOLVED, singleTask.isSolved() ? 1 : 0);

        return values;
    }

}
