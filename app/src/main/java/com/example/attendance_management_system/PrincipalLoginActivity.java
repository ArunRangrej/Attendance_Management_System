package com.example.attendance_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PrincipalLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);
    }

    public void validate_principal(View view) {
        EditText username = (EditText) findViewById(R.id.principal_username);
        EditText password = (EditText) findViewById(R.id.principal_password);
        String user_name = username.getText().toString();
        String pass_word = password.getText().toString();

        if (TextUtils.isEmpty(user_name)) {
            username.setError("Invalid User Name");
        } else if (TextUtils.isEmpty(pass_word)) {
            password.setError("enter password");
        } else {
            if (user_name.equals("principal") & pass_word.equals("MSRIT")) {
                Intent intent = new Intent(this, principal_home.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
            }

        }

    }
}