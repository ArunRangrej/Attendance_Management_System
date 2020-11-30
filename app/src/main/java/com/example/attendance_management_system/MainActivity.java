package com.example.attendance_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
        Intent intent =new Intent(MainActivity.this,add_student.class);
        startActivity(intent);
    }
}