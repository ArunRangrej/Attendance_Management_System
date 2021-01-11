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

        if (!validateName() | !validateDOB() | !validateContact() | !validatePassword() | !validateEmail() | !validateUsn() ) {
            return;
        }
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

    private boolean validateName() {
        String val = Sname.getText().toString().trim();
        if (val.isEmpty()) {
            Sname.setError("Name can not be empty");
            return false;
        } else {
            Sname.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String val = Spassword.getText().toString().trim();
        String checkPassword ="^{4,}$";
        if (val.isEmpty()) {
            Spassword.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkPassword)) {
            Spassword.setError("Password should contain 4 characters!");
            return false;
        } else {
            Spassword.setError(null);
            return true;
        }
    }
    private boolean validateDOB() {
        String val = SDOB.getText().toString().trim();
        String pattern = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

        if (val.isEmpty()) {
            SDOB.setError("DOB can not be empty");
            return false;
        }else if (!val.matches(pattern)) {
            SDOB.setError("Enter Valid DOB!");
            return false;
        }
        else {
            SDOB.setError(null);
            return true;
        }
    }
    private boolean validateContact() {
        String val = Sphno.getText().toString().trim();
        String Pattern = "^[6-9]{1}[0-9]{9}$";
        if (!val.matches(Pattern)) {
            Sphno.setError("Enter valid phone number!");
            return false;
        }
        else {
            Sphno.setError(null);
            return true;
        }
    }
    private boolean validateEmail() {
        String val = Smail.getText().toString().trim();
        String checkEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (val.isEmpty()) {
            Smail.setError("Field can not be empty");
            return false;
        } else if (!val.matches(checkEmail)) {
            Smail.setError("Invalid Email!");
            return false;
        } else {
            Smail.setError(null);
            return true;
        }
    }


    private boolean validateUsn() {
        String val = Susn.getText().toString().trim();
        String Pattern = "^[1]{1}+[M]{1}+[SY]{1}+[1-9]{1}+[1-9]{1}+[M]+[C]+[A]+[0-9]{1}+[0-9]{1}$";
        if (!val.matches(Pattern)) {
            Susn.setError("Enter valid USN number!");
            return false;
        }
        else {
            Susn.setError(null);
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
