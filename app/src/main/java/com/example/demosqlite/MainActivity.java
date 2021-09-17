package com.example.demosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.example.demosqlite.data.DemoSqliteDAO;
import com.example.demosqlite.data.DemoSqliteDbHelper;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find ListView to populate
        listView = findViewById(R.id.listView);

        //Init DAO class
        DemoSqliteDAO sqliteDAO = new DemoSqliteDAO(MainActivity.this, false);

        // Query all students from the database and get a cursor back
        Cursor studentCursors = sqliteDAO.getAllStudents(MainActivity.this);

        // Setup cursor adapter using cursor from last step
        DemoCursorAdapter adapter = new DemoCursorAdapter(MainActivity.this, studentCursors);

        //Set adapter into listview
        listView.setAdapter(adapter);

        // Switch to new cursor and update contents of ListView
        //adapter.changeCursor(newCursor);
    }
}