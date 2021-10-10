package com.example.demosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import static com.example.demosqlite.MainActivity.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demosqlite.data.DemoSqliteDAO;
import com.example.demosqlite.model.Student;


public class AddStudentActivity extends AppCompatActivity {
    private EditText edtName;
    private Button btnAdd;
    private Button btnBack;
    private Spinner spnClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Student");
        setContentView(R.layout.add_students);
        edtName = (EditText) findViewById(R.id.edtName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBack = (Button) findViewById(R.id.btnBack);
        spnClass = (Spinner) findViewById(R.id.spnClass);

        //data spiner
        String[] classList = {"18DTHA1","18DTHB2", "18DTHC3", "18DTHD1", "18DTHC2", "18DTHB3"};
        ArrayAdapter<String> adapterSpn = new ArrayAdapter<String>(this,R.layout
                                                                    .support_simple_spinner_dropdown_item,classList);
        spnClass.setAdapter(adapterSpn);
        //add student
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = createStudent();
                if (student != null){
                    boolean result = DemoSqliteDAO.addStudent(AddStudentActivity.this, student);
                    if (result){
                        adapter.notifyDataSetChanged();
                        Toast.makeText(AddStudentActivity.this, "Add to database successfully!",
                                Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddStudentActivity.this,MainActivity.class));
                        finish();
                    }
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private Student createStudent(){
        String name = edtName.getText().toString();
        String classroom  = spnClass.getSelectedItem().toString();
        Student student = new Student(name,classroom);
        return student;
    }
}
