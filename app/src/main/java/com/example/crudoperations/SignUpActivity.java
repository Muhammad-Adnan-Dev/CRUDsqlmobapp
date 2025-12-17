package com.example.crudoperations;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText username, email, password, dob;
    Button signup;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        dob = findViewById(R.id.etDOB);
        signup = findViewById(R.id.btnSignUp);

        db = new DBHelper(this);

        signup.setOnClickListener(v -> {
            Boolean result = db.insertUser(
                    username.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString(),
                    dob.getText().toString()
            );

            Toast.makeText(this,
                    result ? "Account Created" : "User Exists",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
