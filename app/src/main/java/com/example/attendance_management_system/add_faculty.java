package com.example.attendance_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                if (!validateName() | !validateSub() | !validateContact() | !validateEmail() | !validatePassword()) {
                    return;
                }

                String faculty_name = textFacultyName.getText().toString().trim();
                String subject_name = textSubject.getText().toString().trim();
                String phone_no = textcontact.getText().toString().trim();
                String mail = textmail.getText().toString().trim();
                String password = textpassword.getText().toString().trim();
                Faculty faculty = new Faculty(faculty_name,phone_no,subject_name,mail,password);
                Faculty_Database.child(phone_no).setValue(faculty);
                Toast.makeText(getApplicationContext(),"Teacher added successfully", Toast.LENGTH_LONG).show();
                finish();
            }

        });


    }

    public void go_back(View view)
    {
        Intent intent =new Intent(add_faculty.this,principal_home.class);
        startActivity(intent);
    }

    private boolean validateName() {
        String val = textFacultyName.getText().toString().trim();
        if (val.isEmpty()) {
            textFacultyName.setError("Field can not be empty");
            return false;
        } else {
            textFacultyName.setError(null);
            return true;
        }
    }

    private boolean validateSub() {
        String val = textSubject.getText().toString().trim();
        if (val.isEmpty()) {
            textSubject.setError("Field can not be empty");
            return false;
        } else {
            textSubject.setError(null);
            return true;
        }
    }
    private boolean validateContact() {
        String val = textcontact.getText().toString().trim();
        String Pattern = "^[6-9]{1}[0-9]{9}$";
         if (!val.matches(Pattern)) {
            textcontact.setError("Enter proper mobile number!");
            return false;
        }
        else {
            textcontact.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        String val = textmail.getText().toString().trim();
        String checkEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (val.isEmpty()) {
            textmail.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            textmail.setError("Invalid Email!");
            return false;
        } else {
            textmail.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String val = textpassword.getText().toString().trim();
        String checkPassword ="^[a-zA-Z0-9]{4,}$";
        if (val.isEmpty()) {
            textpassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            textpassword.setError("Password should contain 4 characters!");
            return false;
        } else {
            textpassword.setError(null);
            return true;
        }
    }

}