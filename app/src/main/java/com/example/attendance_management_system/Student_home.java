package com.example.attendance_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Student_home extends AppCompatActivity {
    String message,myclass;
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    Toolbar mToolbar;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Student");
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("usn");
        myclass = bundle.getString("class_selected");
        mToolbar=(Toolbar)findViewById(R.id.ftoolbar);
        mToolbar.setTitle(message+"'s Dashboard"+"("+date+")");
        TextView txtView = (TextView) findViewById(R.id.textView1);


        txtView.setText("Welcome :"+message);

    }
    public void viewAttendance(View v){
        Bundle basket = new Bundle();
        basket.putString("sid", message);
        basket.putString("class_selected", myclass);
        Intent intent = new Intent(this, studentAttendanceActivity.class);
        intent.putExtras(basket);
        startActivity(intent);
    }

    public void logoutStudent(View view) {
        Intent logoutStudent=new Intent(Student_home.this,DashboardActivity.class);
        logoutStudent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logoutStudent);
    }
    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            finish();
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        }
        else {
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }
}
