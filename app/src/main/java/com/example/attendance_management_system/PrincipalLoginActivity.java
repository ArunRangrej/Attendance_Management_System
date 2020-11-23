package com.example.attendance_management_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PrincipalLoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);
    }

    public void validate_principal(View view) {
        final EditText username = (EditText) findViewById(R.id.principal_username);
        final EditText password = (EditText) findViewById(R.id.principal_password);
        final String user_name = username.getText().toString();
        final String pass_word = password.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("principal");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String dbusername = dataSnapshot.child("username").getValue(String.class);
                String dbpassword = dataSnapshot.child("password").getValue(String.class);
                if (user_name.equals(dbusername) && pass_word.equals(dbpassword) ) {
                    Intent intent =new Intent(PrincipalLoginActivity.this,principal_home.class);
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