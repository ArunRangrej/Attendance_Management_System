package com.example.attendance_management_system;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addteacher extends AppCompatActivity {
    EditText Tname;
    EditText Tid;
    EditText tpassword;
    String tname,tid,dept,tpass;
    Spinner Department;
    Button addButton;
    DatabaseReference databaseTeacher;
    Toolbar mToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteacher);
        databaseTeacher = FirebaseDatabase.getInstance().getReference("Teacher");
        Tname =  (EditText) findViewById(R.id.student_name);
        Tid =  (EditText) findViewById(R.id.subject);
        Department = (Spinner) findViewById(R.id.student_department);
        tpassword =  (EditText) findViewById(R.id.editText5);
        mToolbar=(Toolbar)findViewById(R.id.ftoolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add/Remove Teacher");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addTeacher(View v){
        if (!validateName()  | !validatePassword()) {
            return;
        }
        tname = Tname.getText().toString();
        tid = Tid.getText().toString();
        dept = Department.getSelectedItem().toString();
        tpass = tpassword.getText().toString();

        if (!(TextUtils.isEmpty(Tid.getText().toString()))) {
            // String id = databaseTeacher.push().getKey();
            Teacher teacher =new Teacher(tname ,tid ,dept,tpass);
            databaseTeacher.child(tid).setValue(teacher);
            Toast.makeText(getApplicationContext(),"Teacher added successfully", Toast.LENGTH_LONG).show();
            finish();

        }else {
            Toast.makeText(getApplicationContext(),"fields cannot be empty", Toast.LENGTH_LONG).show();
        }
    }
    public void removeTeacher(View v){
        if (!TextUtils.isEmpty(Tid.getText().toString())) {
            tid = Tid.getText().toString();
            databaseTeacher.child(tid).setValue(null);
            Toast.makeText(getApplicationContext(),"Teacher removed successfully", Toast.LENGTH_LONG).show();
            finish();

        }else {
            Toast.makeText(getApplicationContext(),"id cannot be empty", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateName() {
        String val = Tname.getText().toString().trim();
        if (val.isEmpty()) {
            Tname.setError("Field can not be empty");
            return false;
        } else {
            Tname.setError(null);
            return true;
        }
    }



    private boolean validatePassword() {
        String val = tpassword.getText().toString().trim();
        String checkPassword ="^{4,}$";
        if (val.isEmpty()) {
            tpassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            tpassword.setError("Password should contain 4 characters!");
            return false;
        } else {
            tpassword.setError(null);
            return true;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
