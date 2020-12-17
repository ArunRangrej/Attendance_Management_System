package com.example.attendance_management_system;

import android.content.Intent;
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
        registerButton=(Button)findViewById(R.id.student_register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!validateName() | !validateDOB() | !validateContact() | !validateAddress() | !validateEmail() | !validateUsn() ) {
                    return;
                }

                String student_firstname = firstname.getText().toString().trim();
                String student_lastname = lastname.getText().toString().trim();
                String student_DOB = DOB.getText().toString().trim();
                String student_address = address.getText().toString().trim();
                String student_phno = phno.getText().toString().trim();
                String student_mail = mail.getText().toString().trim();
                String student_usn = usn.getText().toString().trim();
                try {
                    Student student = new Student(student_firstname,student_lastname,student_DOB,student_address,student_phno,student_mail,student_usn);
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

    public void go_back(View view)
    {
        Intent intent =new Intent(add_student.this,principal_home.class);
        startActivity(intent);
    }
    private boolean validateName() {
        String val = firstname.getText().toString().trim();
        if (val.isEmpty()) {
            firstname.setError("First name can not be empty");
            return false;
        } else {
            firstname.setError(null);
            return true;
        }
    }
    private boolean validateAddress() {
        String val = address.getText().toString().trim();
        if (val.isEmpty()) {
            address.setError("Address can not be empty");
            return false;
        } else {
            address.setError(null);
            return true;
        }
    }
    private boolean validateDOB() {
        String val = DOB.getText().toString().trim();
        String pattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

        if (val.isEmpty()) {
            DOB.setError("DOB can not be empty");
            return false;
        }else if (!val.matches(pattern)) {
            DOB.setError("Enter Valid DOB!");
            return false;
        }
        else {
            DOB.setError(null);
            return true;
        }
    }
    private boolean validateContact() {
        String val = phno.getText().toString().trim();
        String Pattern = "^[6-9]{1}[0-9]{9}$";
        if (!val.matches(Pattern)) {
            phno.setError("Enter valid phone number!");
            return false;
        }
        else {
            phno.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        String val = mail.getText().toString().trim();
        String checkEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (val.isEmpty()) {
            mail.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            mail.setError("Invalid Email!");
            return false;
        } else {
            mail.setError(null);
            return true;
        }
    }


    private boolean validateUsn() {
        String val = usn.getText().toString().trim();
        String Pattern = "^[1]{1}+[M]{1}+[SY]{1}+[1-9]{1}+[1-9]{1}+[M]+[C]+[A]+[0-9]{1}+[0-9]{1}$";
         if (!val.matches(Pattern)) {
            usn.setError("Enter valid USN number!");
            return false;
        }
        else {
            usn.setError(null);
            return true;
        }
    }


}