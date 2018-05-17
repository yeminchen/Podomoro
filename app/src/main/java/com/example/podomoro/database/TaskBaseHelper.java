package com.example.podomoro.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.podomoro.database.TaskDbSchema.TaskTable;

/**
 * Created by cym on 3/12/18.
 */

public class TaskBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "taskBase.db";

    private Context mContext;

    public TaskBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TaskTable.NAME + "(" +
                    " _id integer primary key autoincrement, " +
                    TaskTable.Cols.UUID + ", " +
                    TaskTable.Cols.TITLE + ", " +
                    TaskTable.Cols.CONTENT + ", " +
                    TaskTable.Cols.SOLVED + ", " +
                    TaskTable.Cols.REMAIN +
                ")"
        );
        Toast.makeText(mContext, "Created succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
