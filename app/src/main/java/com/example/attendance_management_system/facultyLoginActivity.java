package com.example.attendance_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.attendance_management_system.DB_Helper.faculty_connectivity;
import com.example.attendance_management_system.DB_Connectivity.DB;
public class facultyLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
    }

    public void validate_faculty(View view) {
        EditText username = (EditText) findViewById(R.id.faculty_username);
        EditText password = (EditText) findViewById(R.id.faculty_password);
        String user_name = username.getText().toString();
        String pass_word = password.getText().toString();

        if (TextUtils.isEmpty(user_name))
        {
            username.setError("Invalid User Name");
        }
        else if(TextUtils.isEmpty(pass_word))
        {
            password.setError("enter password");
        }
        DB dbAdapter = new DB(facultyLoginActivity.this);
        faculty_connectivity facultyBean = dbAdapter.validateFaculty(user_name, pass_word);

        if(facultyBean!=null)
        {
            Intent intent = new Intent(facultyLoginActivity.this,faculty_home.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    }
