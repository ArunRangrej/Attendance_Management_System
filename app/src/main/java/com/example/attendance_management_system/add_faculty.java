package com.example.attendance_management_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.attendance_management_system.DB_Helper.faculty_connectivity;
import com.example.attendance_management_system.DB_Connectivity.DB;
public class add_faculty extends AppCompatActivity {
    Button registerButton;
    EditText textFirstName;
    EditText textLastName;
    EditText textmail;
    EditText textcontact;
    EditText textusername;
    EditText textpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);
        textFirstName=(EditText)findViewById(R.id.faculty_first_name);
        textLastName=(EditText)findViewById(R.id.faculty_last_name);
        textcontact=(EditText)findViewById(R.id.faculty_ph_no);
        textmail=(EditText)findViewById(R.id.faculty_mail);
        textusername=(EditText)findViewById(R.id.faculty_username);
        textpassword=(EditText)findViewById(R.id.faculty_password);
        registerButton=(Button)findViewById(R.id.faculty_register_button);

        registerButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String first_name = textFirstName.getText().toString();
                String last_name = textLastName.getText().toString();
                String phone_no = textcontact.getText().toString();
                String mail = textmail.getText().toString();
                String username = textusername.getText().toString();
                String password = textpassword.getText().toString();

                if (TextUtils.isEmpty(first_name)) {
                    textFirstName.setError("please enter firstname");
                }
                else if (TextUtils.isEmpty(last_name)) {
                    textLastName.setError("please enter lastname");
                }
                else if (TextUtils.isEmpty(phone_no)) {
                    textcontact.setError("please enter phoneno");
                }

                else if (TextUtils.isEmpty(mail)) {
                    textmail.setError("enter mail");
                }
                else if (TextUtils.isEmpty(username)) {
                    textcontact.setError("please enter username");
                }
                else if (TextUtils.isEmpty(password)) {
                    textpassword.setError("enter password");
                }
                else {


                }

            }
        });

    }
}