package com.example.podomoro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import com.example.podomoro.database.TaskBaseHelper;
import com.google.android.gms.gcm.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cym on 4/8/18.
 */
public class TaskLabTest {
    TaskLab mTaskLab;
    List<SingleTask> list;
    SQLiteDatabase mDatabase;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        mTaskLab = TaskLab.get(appContext);

    }

    @Test
    public void testGetTasks() throws Exception {
        list = mTaskLab.getTasks();
        for (SingleTask task : list) {
            assertNotEquals(0, task.getRemain());
        }
    }


}