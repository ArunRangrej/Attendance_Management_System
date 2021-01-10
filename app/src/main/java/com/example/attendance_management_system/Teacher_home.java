package com.example.attendance_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Teacher_home extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String item;
    String message;
    ArrayList SubjectList = new ArrayList<>();
    Toolbar mToolbar;
    Spinner Sub_spinner;
    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Sub_spinner = (Spinner) findViewById(R.id.spinner3);

        //to get username from login page
        Bundle bundle1 = getIntent().getExtras();
        message = bundle1.getString("message");
        mToolbar=(Toolbar)findViewById(R.id.takeattendancebar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(message+"'s Dashboard  - "+date);

        TextView txtView = (TextView) findViewById(R.id.textView1);
        txtView.setText("Welcome : "+message);
        spinner2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> categories = new ArrayList<String>();
        categories.add("3rd Sem");
        categories.add("5th Sem");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);


}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        //  Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        SubjectList.clear();
        DatabaseReference databasesubjects = FirebaseDatabase.getInstance().getReference("Subjects");
        databasesubjects.child("MCA").child(item).orderByChild("teacher_id")
                .equalTo(message).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    System.out.println(dsp.child("subject").getValue().toString());
                    SubjectList.add(dsp.child("subject").getValue().toString());

                }
                OnStart(SubjectList);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }

        });
    }
    public void OnStart(ArrayList<String> SubjectList) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SubjectList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Sub_spinner.setAdapter(dataAdapter);
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void takeAttendanceButton(View v){
        Bundle basket= new Bundle();
        basket.putString("class_selected", item);
        System.out.println(Sub_spinner.getSelectedItem().toString());
        basket.putString("subject_selected", Sub_spinner.getSelectedItem().toString());
        basket.putString("tid", message);


        Intent intent = new Intent(this, takeAttendance.class);
        intent.putExtras(basket);
        startActivity(intent);
    }
    public void  previous_records(View v){
        Bundle basket= new Bundle();
        basket.putString("class_selected", item);
        basket.putString("subject_selected", Sub_spinner.getSelectedItem().toString());
        basket.putString("tid", message);


        Intent intent = new Intent(this, teacher_attendanceSheet.class);
        intent.putExtras(basket);
        startActivity(intent);
    }


    public void logoutTeacher(View view) {
        Intent logoutTeacher=new Intent(Teacher_home.this,DashboardActivity.class);
        logoutTeacher.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logoutTeacher);
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
