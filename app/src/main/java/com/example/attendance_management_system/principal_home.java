package com.example.attendance_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class principal_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_home);
        Button addFaculty = (Button) findViewById(R.id.principal_add_faculty);
        Button addStudent = (Button) findViewById(R.id.principal_add_student);
        Button logout = (Button) findViewById(R.id.loout_button);

        addFaculty.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(principal_home.this,add_faculty.class);
                startActivity(intent);
            }
        });

        addStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(principal_home.this,add_student.class);
                startActivity(intent);
            }
        });



        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent =new Intent(principal_home.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}