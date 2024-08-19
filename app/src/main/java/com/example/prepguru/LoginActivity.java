package com.example.prepguru;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prepguru.database.DatabaseHelper;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);

        databaseHelper = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()) {
                    Toast.makeText(LoginActivity.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor cursor = databaseHelper.login(email, password);
                if (cursor.getCount() > 0) {

                   /* SharedPreferences sharedPref = getSharedPreferences("application", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                            String name = cursor.getString(2);
                            int id = cursor.getInt(0);
                            editor.putInt("id", id);
                            editor.putString("name", name);
                            Toast.makeText(LoginActivity.this, "" + name + id, Toast.LENGTH_SHORT).show();

                            cursor.moveToNext();
                        }
                    }*/
                    cursor.close();
//                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Enter valid id and password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.prepguru.LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}

