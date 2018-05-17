package com.example.podomoro.database;

/**
 * Created by cym on 2/27/18.
 */

public class TaskDbSchema {
    public static final class TaskTable {
        public static final String NAME = "tasks";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String CONTENT = "content";
            public static final String SOLVED = "solved";
            public static final String REMAIN = "remain";
        }
    }
}
