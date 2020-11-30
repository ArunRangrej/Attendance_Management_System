package com.example.attendance_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.attendance_management_system.Faculty;

public class add_faculty extends AppCompatActivity {
    Button registerButton;
    EditText textFacultyName;
    EditText textSubject;
    EditText textmail;
    EditText textcontact;
    EditText textpassword;
    DatabaseReference Faculty_Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);

        Faculty_Database = FirebaseDatabase.getInstance().getReference("Faculty");

        textFacultyName=(EditText)findViewById(R.id.faculty_name);
        textSubject=(EditText)findViewById(R.id.faculty_subject_name);
        textcontact=(EditText)findViewById(R.id.faculty_ph_no);
        textmail=(EditText)findViewById(R.id.faculty_mail);
        textpassword=(EditText)findViewById(R.id.faculty_password);
        registerButton=(Button)findViewById(R.id.faculty_register_button);

        registerButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String faculty_name = textFacultyName.getText().toString();
                String subject_name = textSubject.getText().toString();
                String phone_no = textcontact.getText().toString();
                String mail = textmail.getText().toString();
                String password = textpassword.getText().toString();
                Faculty faculty = new Faculty(faculty_name,phone_no,subject_name,mail,password);
                Faculty_Database.child(phone_no).setValue(faculty);
                Toast.makeText(getApplicationContext(),"Teacher added successfully", Toast.LENGTH_LONG).show();
                finish();
            }

        });

    }
}