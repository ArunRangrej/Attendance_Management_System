package com.example.attendance_management_system;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Principal_add_link_subjects extends AppCompatActivity {
    ArrayList Teacherlist = new ArrayList<>();
    Spinner Department;
    Spinner Sem;
    Spinner Teacher_id;
    Button addButton;
    EditText Subject;
    String department, sem, teacher_id, subject;
    DatabaseReference databaseTeacher;
    DatabaseReference databasesubjects = FirebaseDatabase.getInstance().getReference("Subjects");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_add_link_subjects);
        Department = (Spinner) findViewById(R.id.department);
        Sem = (Spinner) findViewById(R.id.sem);
        Teacher_id = (Spinner) findViewById(R.id.teacher_id);
        Subject =  (EditText) findViewById(R.id.subject);





        databaseTeacher = FirebaseDatabase.getInstance().getReference("Teacher");
        databaseTeacher.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Teacherlist.add(dsp.child("tid").getValue().toString());

                }
                OnStart(Teacherlist);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
            }

        });

    }
    public void OnStart(ArrayList<String> Teacherlist) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Teacherlist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Teacher_id.setAdapter(dataAdapter);
    }
    public void add_subjects(View v){
        if (!validateSub() ) {
            return;
        }
        department = Department.getSelectedItem().toString();
        sem = Sem.getSelectedItem().toString();
        teacher_id = Teacher_id.getSelectedItem().toString();
        subject = Subject.getText().toString();
        if (!(TextUtils.isEmpty(subject.toString()))) {

            Subjects subj =new Subjects(teacher_id ,subject);
            databasesubjects.child(department).child(sem).child(subject).setValue(subj);
            Toast.makeText(getApplicationContext(),"Subject added successfully", Toast.LENGTH_LONG).show();
            finish();

        }
    }
    private boolean validateSub() {
        String val = Subject.getText().toString().trim();
        if (val.isEmpty()) {
            Subject.setError("Name can not be empty");
            return false;
        } else {
            Subject.setError(null);
            return true;
        }
    }
}