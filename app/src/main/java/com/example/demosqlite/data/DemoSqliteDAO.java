package com.example.demosqlite.data;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 This class is packing all methods that are needed to access database
 **/
public class DemoSqliteDAO {
    //Db contents to access the database
    private SQLiteDatabase db;


    public DemoSqliteDAO(Context context, boolean writeable) {
        if (writeable) {
            db = DemoSqliteDbHelper.getWriteableDatabase(context);
        } else {
            db = DemoSqliteDbHelper.getReadableDatabase(context);
        }
    }

    //By default we get writeable database. Because writeable database can allow us to read and write at the same time.
    public DemoSqliteDAO(Context context) {
        db = DemoSqliteDbHelper.getWriteableDatabase(context);
    }

    /**
     Get all students contents from table students
     **/
    public Cursor getAllStudents(Context context) {
        //Query all student from data
        String query = "SELECT * FROM " + DemoSqliteDbContract.SinhVienEntry.TABLE_NAME;
        return db.rawQuery(query, null);
    }
}
