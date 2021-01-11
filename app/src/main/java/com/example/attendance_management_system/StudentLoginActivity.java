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
public class StudentLoginActivity extends AppCompatActivity {
    Button login;
    DatabaseReference ref;
    String userid,pass;
    String dbpassword;
    String myclass;
    Bundle basket;
    String sem;
    EditText username,password;
    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        login = findViewById(R.id.login);
        username = findViewById(R.id.adminEditText);
        password = findViewById(R.id.adminPasswordEditText);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = username.getText().toString();
                pass = password.getText().toString();
                mDialog=new ProgressDialog(StudentLoginActivity.this);
                mDialog.setMessage("Please Wait..."+userid);
                mDialog.setTitle("Loading");
                mDialog.show();
                basket = new Bundle();
                basket.putString("message", userid);
                sem="3rd Sem";
                ref = FirebaseDatabase.getInstance().getReference();
                DatabaseReference dbuser = ref.child("Student").child(sem).child(userid);
                System.out.println("Testing " +dbuser);

                dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        mDialog.dismiss();
                        dbpassword = dataSnapshot.child("password").getValue(String.class);
                        if(dbpassword != null){
                            verify(dbpassword,sem);
                        }
                        else
                        {
                            sem="5th Sem";
                            ref = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference dbuser = ref.child("Student").child(sem).child(userid);
                            System.out.println("Testing " +dbuser);

                            dbuser.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    mDialog.dismiss();
                                    dbpassword = dataSnapshot.child("password").getValue(String.class);
                                        verify(dbpassword,sem);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(getApplicationContext(), "database error", Toast.LENGTH_LONG).show();
                                }
                            });
                        }


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
        Intent intent = new Intent(StudentLoginActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
    public void verify(String dbpassword,String myclass) {
        if(userid.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Username cannot be empty", Toast.LENGTH_LONG).show();
        }
        else if (pass.equalsIgnoreCase(this.dbpassword) ) {
            //  if (userid.equalsIgnoreCase("admin") && pass.equals("admin")) {
            mDialog.dismiss();
            Intent intent = new Intent(this, Student_home.class);
            Bundle basket= new Bundle();
            basket.putString("usn", userid);
            basket.putString("class_selected", myclass);
            intent.putExtras(basket);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
            finish();
            //  }
        }
        else {
            Toast.makeText(getApplicationContext(), "Please Enter valid user id or password", Toast.LENGTH_LONG).show();
        }
    }
}
