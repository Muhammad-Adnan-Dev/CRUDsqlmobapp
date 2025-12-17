package com.example.crudoperations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button signin, signup, delete;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        signin = findViewById(R.id.btnSignIn);
        signup = findViewById(R.id.btnSignUp);
        delete = findViewById(R.id.btnDelete);

        db = new DBHelper(this);

        signin.setOnClickListener(v -> {
            Boolean check = db.checkUser(
                    email.getText().toString(),
                    password.getText().toString()
            );

            if (check) {
                Intent i = new Intent(this, ProfileActivity.class);
                i.putExtra("email", email.getText().toString());
                startActivity(i);
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        signup.setOnClickListener(v ->
                startActivity(new Intent(this, SignUpActivity.class)));

        delete.setOnClickListener(v -> {
            Boolean result = db.deleteUser(email.getText().toString());
            Toast.makeText(this,
                    result ? "Account Deleted" : "User not found",
                    Toast.LENGTH_SHORT).show();
        });
    }
}
