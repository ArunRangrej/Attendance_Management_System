package com.example.attendance_management_system;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class principal_home extends AppCompatActivity {
    DatabaseReference ref;
    DatabaseReference dbStudent;
    DatabaseReference dbAttendance;
    DatabaseReference dbadmin;
    Toolbar mToolbar;
    private static long back_pressed;

    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    ArrayList Studentlist = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_home);
        mToolbar=(Toolbar)findViewById(R.id.ftoolbar);
        mToolbar.setTitle("Principal Dashboard : "+"("+date+")");
        ref = FirebaseDatabase.getInstance().getReference();
        dbStudent = ref.child("Student");
        dbAttendance = ref.child("attendance");





    }
    public void AddTeacherButton(View v){
        Intent intent = new Intent(this, addteacher.class);
        startActivity(intent);
    }
    public void AddStudentButton(View v){
        Intent intent = new Intent(this, addstudent.class);
        startActivity(intent);
    }
    public void attendanceRecord(View v){
        Intent intent = new Intent(this, Principal_attendanceSheet.class);
        startActivity(intent);
    }

    public void link_subjects(View v){
        Intent intent = new Intent(this, Principal_add_link_subjects.class);
        startActivity(intent);
    }


    public void logout(View view) {

        Intent logout=new Intent(principal_home.this,DashboardActivity.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logout);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void changepassword(View view) {
        dbadmin=ref.child("principal");

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Set your new password");
        final LayoutInflater inflater = this.getLayoutInflater();
        View add_menu_layout = inflater.inflate(R.layout.changepassword, null);
        final EditText password=(EditText)add_menu_layout.findViewById(R.id.newpassword);
        alertDialog.setView(add_menu_layout);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, int which) {
                if (!TextUtils.isEmpty(password.getText().toString()))
                {
                    dbadmin.child("password").setValue(password.getText().toString());
                    Toast.makeText(principal_home.this, "Successfully Changed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(principal_home.this, "Please Enter New Password", Toast.LENGTH_SHORT).show();
                }



            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();


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