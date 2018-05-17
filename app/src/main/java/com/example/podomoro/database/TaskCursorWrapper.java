package com.example.podomoro.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.podomoro.SingleTask;
import com.example.podomoro.database.TaskDbSchema.TaskTable;

import java.util.UUID;

/**
 * Created by cym on 3/12/18.
 */

public class TaskCursorWrapper extends CursorWrapper{

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public SingleTask getTask(){
        String uuidString = getString(getColumnIndex(TaskTable.Cols.UUID));
        String title = getString(getColumnIndex(TaskTable.Cols.TITLE));
        String content = getString(getColumnIndex(TaskTable.Cols.CONTENT));
        int remain = getInt(getColumnIndex(TaskTable.Cols.REMAIN));
        int isSolved = getInt(getColumnIndex(TaskTable.Cols.SOLVED));

        SingleTask singleTask = new SingleTask(UUID.fromString(uuidString));
        singleTask.setTitle(title);
        singleTask.setContent(content);
        singleTask.setRemain(remain);
        singleTask.setSolved(isSolved != 0);

        return singleTask;
    }

}
