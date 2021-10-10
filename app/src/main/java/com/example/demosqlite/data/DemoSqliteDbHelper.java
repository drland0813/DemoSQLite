package com.example.demosqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DemoSqliteDbHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "demoSqlite.db";
    private static final int DATABASE_VERSION = 1;

    public DemoSqliteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    static public SQLiteDatabase getReadableDatabase(Context context) {
        //Init database helper, and database content to communicate with sqlite database
        DemoSqliteDbHelper mDbHelper = new DemoSqliteDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db;
    }

    static public SQLiteDatabase getWriteableDatabase(Context context) {
        //Init database helper, and database content to communicate with sqlite database
        DemoSqliteDbHelper mDbHelper = new DemoSqliteDbHelper(context);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        return db;
    }
}
