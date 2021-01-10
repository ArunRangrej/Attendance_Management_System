package com.example.attendance_management_system;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addstudent extends AppCompatActivity {
    EditText Sname;
    EditText Susn;
    EditText Spassword;
    EditText SDOB;
    EditText Sphno;
    EditText Smail;
    Spinner Sclass;
    Spinner Sdept;


    String sname,susn,spass,sdob,sphno,smail,sclass,sdept;


    DatabaseReference databaseStudent;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        databaseStudent = FirebaseDatabase.getInstance().getReference("Student");

        Sname =  (EditText) findViewById(R.id.student_name);
        Susn =  (EditText) findViewById(R.id.subject);
        Spassword = (EditText) findViewById(R.id.student_password);
        SDOB = (EditText) findViewById(R.id.student_DOB);
        Sphno = (EditText) findViewById(R.id.student_ph_no);
        Smail = (EditText) findViewById(R.id.student_mail);
        Sclass = (Spinner) findViewById(R.id.student_class);
        Sdept = (Spinner) findViewById(R.id.student_department);
        mToolbar=(Toolbar)findViewById(R.id.ftoolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Add/Remove Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void addStudent(View v){


        if (!(TextUtils.isEmpty(Susn.getText().toString()))) {
            //String id = databaseStudent.push().getKey();
            sname = Sname.getText().toString();
            susn = Susn.getText().toString();
            spass = Spassword.getText().toString();
            sdob = SDOB.getText().toString();
            sphno = Sphno.getText().toString();
            smail = Smail.getText().toString();

            sclass = Sclass.getSelectedItem().toString();
            sdept = Sdept.getSelectedItem().toString();


            Student student =new Student(sname , susn, spass, sdob, sphno, smail, sclass, sdept);
            databaseStudent.child(sclass).child(susn).setValue(student);
            Toast.makeText(getApplicationContext(),"student added successfully", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(getApplicationContext(),"fields cannot be empty", Toast.LENGTH_LONG).show();
        }
    }
    public void removeStudent(View v){
        if (!TextUtils.isEmpty(Susn.getText().toString())) {
            susn = Susn.getText().toString();
            sclass = Sclass.getSelectedItem().toString();
            databaseStudent.child(sclass).child(susn).setValue(null);
            Toast.makeText(getApplicationContext(),"Student removed successfully", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(getApplicationContext(),"id cannot be empty", Toast.LENGTH_LONG).show();
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
