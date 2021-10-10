package com.example.demosqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.demosqlite.data.DemoSqliteDbContract;

public class DemoCursorAdapter extends CursorAdapter {
    public DemoCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }
    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.fragment_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Init and find all needed view to display data
        TextView studentIdTv = view.findViewById(R.id.studentId);
        TextView studentFullNameTv = view.findViewById(R.id.studentName);
        TextView studentClassIdTv = view.findViewById(R.id.studentClassId);

        // Extract properties from cursor
        String studentId = "" + cursor.getString(cursor.getColumnIndex(DemoSqliteDbContract.SinhVienEntry._ID));
        String studentFullName =
                cursor.getString(cursor.getColumnIndex(DemoSqliteDbContract.SinhVienEntry.COLUMNS_FULL_NAME));
        String studentClassId =
                cursor.getString(cursor.getColumnIndex(DemoSqliteDbContract.SinhVienEntry.COLUMNS_CLASS_ID));
        // Populate fields with extracted properties
        studentIdTv.setText(studentId);
        studentFullNameTv.setText(studentFullName);
        studentClassIdTv.setText(studentClassId);
    }
}
