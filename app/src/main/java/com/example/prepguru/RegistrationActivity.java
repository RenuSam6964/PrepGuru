package com.example.prepguru;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prepguru.database.DatabaseHelper;

import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button registerButton;

    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        emailEditText = findViewById(R.id.emailEditText1);
        passwordEditText = findViewById(R.id.passwordEditText1);
        registerButton = findViewById(R.id.register1);

        databaseHelper = new DatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Enter email ,name and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()) {
                    Toast.makeText(RegistrationActivity.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (databaseHelper.checkusername(email)){
                    Toast.makeText(RegistrationActivity.this, "User Already Exist", Toast.LENGTH_SHORT).show();
                }
                if(databaseHelper.insertData(email,password)){
                    Toast.makeText(RegistrationActivity.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(RegistrationActivity.this, "Registered Unsucessful", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

