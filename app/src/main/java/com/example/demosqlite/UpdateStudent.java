package com.example.demosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.demosqlite.MainActivity.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demosqlite.data.DemoSqliteDAO;
import com.example.demosqlite.model.Student;

public class UpdateStudent extends AppCompatActivity {
    private TextView txtID;
    private EditText edtName;
    private Button btnSave;
    private Button btnDelete;
    private Button btnBack;
    private Spinner spnClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Info");
        setContentView(R.layout.update_students);
        txtID = (TextView) findViewById(R.id.txtID);
        edtName = (EditText) findViewById(R.id.edtName);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnBack = (Button) findViewById(R.id.btnBack);
        spnClass = (Spinner) findViewById(R.id.spnClass);
        //data spine
        String[] classList = {"18DTHA1","18DTHB2", "18DTHC3", "18DTHD1", "18DTHC2", "18DTHB3"};
        ArrayAdapter<String> adapterSpn = new ArrayAdapter<String>(this,R.layout
                                                                .support_simple_spinner_dropdown_item,classList);
        spnClass.setAdapter(adapterSpn);
        Intent intent = getIntent();
        //set data
        Student student = new Student();
        student.setmID(Integer.parseInt(intent.getStringExtra("id")));
        student.setmFullName(intent.getStringExtra("name"));
        student.setmClass(intent.getStringExtra("class"));


        txtID.setText(student.getmID()+"");
        edtName.setText(student.getmFullName());
        setSelectedItems(spnClass,student.getmClass());

        //save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student tempStudent = tempData();
                boolean result = DemoSqliteDAO.updateStudent(UpdateStudent.this,tempStudent);
                if (result){
                    adapter.notifyDataSetChanged();
                    Toast.makeText(UpdateStudent.this, "Save successfully!",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(UpdateStudent.this,MainActivity.class));
                    finish();
                }
            }
        });

        //delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student tempStudent = tempData();
                boolean result = DemoSqliteDAO.deleteStudent(UpdateStudent.this,tempStudent.getmID());
                if (result){
                    adapter.notifyDataSetChanged();
                    Toast.makeText(UpdateStudent.this, "Delete successfully!",
                            Toast.LENGTH_LONG).show();
                    startActivity(new Intent(UpdateStudent.this,MainActivity.class));
                    finish();
                }
            }
        });


        //back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private Student tempData(){
        Student tempStudent = new Student();
        tempStudent.setmID(Integer.parseInt(txtID.getText().toString()));
        tempStudent.setmFullName(edtName.getText().toString());
        tempStudent.setmClass(spnClass.getSelectedItem().toString());
        return  tempStudent;
    }

    //Set Item đang được chọn của Spinner
    private void setSelectedItems(Spinner spinner, String values){
        for (int i = 0; i < spinner.getAdapter().getCount(); i++){
            if (spinner.getAdapter().getItem(i).equals(values)){
                spinner.setSelection(i);
                return;
            }
        }
    }
}
