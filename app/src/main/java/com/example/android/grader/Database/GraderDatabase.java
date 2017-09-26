package com.example.android.grader.Database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Gauri Gadkari on 9/25/17.
 */

@Database(name = GraderDatabase.NAME, version = GraderDatabase.VERSION)
public class GraderDatabase {
    @SuppressWarnings("WeakerAccess")
    public static final String NAME = "MyDataBase";

    @SuppressWarnings("WeakerAccess")
    public static final int VERSION = 1;
}