package com.example.attendance_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class faculty_home extends AppCompatActivity {
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_home);
        Button take_attendance = (Button) findViewById(R.id.Take_Attendance);
        Bundle bundle1 = getIntent().getExtras();
        message = bundle1.getString("message");
        EditText username = (EditText) findViewById(R.id.id_display);
        username.setText("Welcome "+message);
        take_attendance.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(faculty_home.this,Take_student_attendance.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle basket;
                basket = new Bundle();
                basket.putString("message", message);
                intent.putExtras(basket);
                startActivity(intent);
            }
        });
    }
}