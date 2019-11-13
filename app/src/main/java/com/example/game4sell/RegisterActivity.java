package com.example.game4sell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game4sell.Database.DatabaseHelper;
import com.example.game4sell.Model.User;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText emailText;
    EditText passwordText;
    EditText usernameText;
    TextView usernameLabel, emailLabel, passwordLabel;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        databaseHelper = new DatabaseHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String email = emailText.getText().toString();

                    if(!databaseHelper.isEmailExists(email)){
                        addUser();
                        Intent login = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(login);
                    }else{
                        Snackbar.make(registerBtn, "User already exist ",
                                Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    private void initViews(){
        emailText = findViewById(R.id.emailEditText);
        passwordText = findViewById(R.id.passwordEditText);
        usernameText = findViewById(R.id.usernameEditText);
        usernameLabel = findViewById(R.id.usernameTextView);
        passwordLabel = findViewById(R.id.passwordTextView);
        emailLabel = findViewById(R.id.emailTextView);
        registerBtn = findViewById(R.id.registerButton);
    }

    public void addUser(){

        String username = usernameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        boolean result = databaseHelper.addUser(new User(email, password, username));

        Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show();

        Log.i("LQS", email);
        Log.i("LQS", password);
        Log.i("LQS", username);
    }

    public boolean validate(){
        boolean valid = false;

        String username = usernameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if(username.isEmpty()){
            valid = false;
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
        } else{
            valid = true;
        }

        if(email.isEmpty()){
            valid = false;
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
        } else{
            valid = true;
        }

        if(password.isEmpty()){
            valid = false;
            Toast.makeText(this,"All fields are required", Toast.LENGTH_SHORT).show();
        } else{
            valid = true;
        }

        return valid;
    }
}
