package com.example.attendance_management_system;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ourinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourinfo);
    }
    @Override
    public void onBackPressed() {
            super.onBackPressed();
        Intent intent = new Intent(ourinfo.this, DashboardActivity.class);
        startActivity(intent);
        finish();
        }
}