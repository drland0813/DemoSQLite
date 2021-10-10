package com.example.demosqlite.data;

import static com.example.demosqlite.data.DemoSqliteDbContract.SinhVienEntry.*;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.demosqlite.model.Student;
import java.util.ArrayList;
import java.util.List;

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

    public List<Student> getListStudents(Context context){
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = DemoSqliteDbHelper.getReadableDatabase(context);
        Cursor cursor  = getAllStudents(context);
        if (cursor.moveToFirst()){
            do {
                Student student = new Student();
                student.setmID(cursor.getInt(0));
                student.setmFullName(cursor.getString(1));
                student.setmClass(cursor.getString(2));
                studentList.add(student);
            }while (cursor.moveToNext());
        }
        db.close();
        return  studentList;
    }

    public  static boolean addStudent(Context context,Student student){
        SQLiteDatabase db = DemoSqliteDbHelper.getWriteableDatabase(context);
        ContentValues values = new ContentValues();
        values.put(COLUMNS_FULL_NAME, student.getmFullName());
        values.put(COLUMNS_CLASS_ID, student.getmClass());
        long row = db.insert(TABLE_NAME,null,values);
        db.close();
        return  (row>0);
    }

    public  static boolean updateStudent(Context context,Student student){
        SQLiteDatabase db = DemoSqliteDbHelper.getWriteableDatabase(context);
        ContentValues values = new ContentValues();
        values.put(COLUMNS_FULL_NAME, student.getmFullName());
        values.put(COLUMNS_CLASS_ID, student.getmClass());
        int row =  db.update(TABLE_NAME, values, _ID+"=?", new String[]{String.valueOf(student.getmID())});
        return (row>0);
    }

    public static boolean deleteStudent(Context context,int idStudent){
        SQLiteDatabase db = DemoSqliteDbHelper.getWriteableDatabase(context);
        int row = db.delete(TABLE_NAME,_ID+"=?",new String[] {String.valueOf(idStudent)});
        return (row>0);
    }

}
