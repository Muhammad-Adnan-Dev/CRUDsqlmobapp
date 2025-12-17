package com.example.crudoperations;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    EditText email, newPass;
    Button update, delete;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email = findViewById(R.id.etEmail);
        newPass = findViewById(R.id.etNewPassword);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);

        db = new DBHelper(this);

        String userEmail = getIntent().getStringExtra("email");
        email.setText(userEmail);

        update.setOnClickListener(v -> {
            Boolean result = db.updatePassword(
                    userEmail,
                    newPass.getText().toString()
            );

            Toast.makeText(this,
                    result ? "Password Updated" : "Failed",
                    Toast.LENGTH_SHORT).show();
        });

        delete.setOnClickListener(v -> {
            Boolean result = db.deleteUser(userEmail);
            Toast.makeText(this,
                    result ? "Account Deleted" : "Failed",
                    Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
