package com.example.attendance_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goto_principal(View view)
    {
        Intent intent =new Intent(MainActivity.this,PrincipalLoginActivity.class);
        startActivity(intent);
    }

    public void goto_faculty(View view)
    {
        Intent intent =new Intent(MainActivity.this,facultyLoginActivity.class);
        startActivity(intent);
    }

    public void test(View view)
    {
        Intent intent =new Intent(MainActivity.this,Take_student_attendance.class);
        startActivity(intent);
    }
}