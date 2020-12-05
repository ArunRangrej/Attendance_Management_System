package com.example.attendance_management_system;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_student extends AppCompatActivity {
    Button registerButton;
    EditText firstname;
    EditText lastname;
    EditText phno;
    EditText mail;
    EditText password;
    EditText usn;
    EditText DOB;
    EditText address;
    DatabaseReference Student_Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Student_Database = FirebaseDatabase.getInstance().getReference("Student");
        firstname=(EditText)findViewById(R.id.student_first_name);
        lastname=(EditText)findViewById(R.id.student_last_name);
        DOB=(EditText)findViewById(R.id.student_DOB);
        address=(EditText)findViewById(R.id.student_address);
        phno=(EditText)findViewById(R.id.student_ph_no);
        mail=(EditText)findViewById(R.id.student_mail);
        usn=(EditText)findViewById(R.id.student_usn);
        password=(EditText)findViewById(R.id.student_password);
        registerButton=(Button)findViewById(R.id.student_register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String student_firstname = firstname.getText().toString();
                String student_lastname = lastname.getText().toString();
                String student_DOB = DOB.getText().toString();
                String student_address = address.getText().toString();
                String student_phno = phno.getText().toString();
                String student_mail = mail.getText().toString();
                String student_usn = usn.getText().toString();
                String student_password = password.getText().toString();
                try {
                    Student student = new Student(student_firstname,student_lastname,student_DOB,student_address,student_phno,student_mail,student_usn,student_password);
                    Student_Database.child(student_usn).setValue(student);
                    Toast.makeText(getApplicationContext(),"Student added successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
                catch (Exception E){
                   // Toast.makeText("ERROR: "+E,"long").show();
                }


            }

        });


    }
}