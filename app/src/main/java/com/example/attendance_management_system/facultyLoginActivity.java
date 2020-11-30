package com.example.attendance_management_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class facultyLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);
    }

    public void validate_faculty(View view) {
        EditText username = (EditText) findViewById(R.id.faculty_username);
        EditText password = (EditText) findViewById(R.id.faculty_password);
        final String user_name = username.getText().toString();
        final String pass_word = password.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Faculty").child(user_name);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dbusername = dataSnapshot.child("fcontact").getValue(String.class);
                String dbpassword = dataSnapshot.child("fpass").getValue(String.class);
                if (user_name.equals(dbusername) && pass_word.equals(dbpassword)) {
                    Intent intent =new Intent(facultyLoginActivity.this,faculty_home.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                    finish();
                    //  }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Enter valid user id or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "database error", Toast.LENGTH_LONG).show();
            }
        });
    }
}




