package com.example.attendance_management_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PrincipalLoginActivity extends AppCompatActivity {
    Button login;
    DatabaseReference ref;
    String userid,pass;
    String dbpassword;
    String dbusername;
    Bundle basket;
    EditText username,password;
    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);
        login = findViewById(R.id.login);
        username = findViewById(R.id.teacher_id);
        password = findViewById(R.id.teacher_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = username.getText().toString();
                pass = password.getText().toString();
                mDialog=new ProgressDialog(PrincipalLoginActivity.this);
                mDialog.setMessage("Please Wait..."+userid);
                mDialog.setTitle("Loading");
                mDialog.show();
                basket = new Bundle();
                basket.putString("message", userid);

                ref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference dbuser = ref.child("principal");


                dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        mDialog.dismiss();
                        dbusername = dataSnapshot.child("username").getValue(String.class);
                        dbpassword = dataSnapshot.child("password").getValue(String.class);
                        verify(dbusername,dbpassword);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "database error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PrincipalLoginActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
    public void verify(String dbusername,String dbpassword) {
        if(userid.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Username cannot be empty", Toast.LENGTH_LONG).show();
        }
        else if (userid.equals(dbusername) && pass.equals(dbpassword) ) {
            //  if (userid.equalsIgnoreCase("admin") && pass.equals("admin")) {
            mDialog.dismiss();
            Intent intent = new Intent(this, principal_home.class);
            intent.putExtras(basket);
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

}