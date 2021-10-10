package com.example.demosqlite;


import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.demosqlite.data.DemoSqliteDAO;
import com.example.demosqlite.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd_Main;
    private ListView listView;
    public  DemoSqliteDAO sqliteDAO;
    public static  DemoCursorAdapter adapter;
    private List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find ListView to populate
        btnAdd_Main = (FloatingActionButton) findViewById(R.id.btnAdd_Main);
        listView = findViewById(R.id.listView);
        //Init DAO class
         sqliteDAO = new DemoSqliteDAO(MainActivity.this, false);
        // Query all students from the database and get a cursor back
        Cursor studentCursors = sqliteDAO.getAllStudents(MainActivity.this);
        // Setup cursor adapter using cursor from last step
         adapter = new DemoCursorAdapter(MainActivity.this, studentCursors);
        studentList = sqliteDAO.getListStudents(MainActivity.this);
        //Set adapter into listview
        listView.setAdapter(adapter);
        // Switch to new cursor and update contents of ListView
        //adapter.changeCursor(newCursor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = studentList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateStudent.class);
                intent.putExtra("id", student.getmID()+"");
                intent.putExtra("name", student.getmFullName());
                intent.putExtra("class", student.getmClass());
                startActivity(intent);
            }
        });
        btnAdd_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivity(intent);
            }
        });
    }
}