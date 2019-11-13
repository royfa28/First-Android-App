package com.example.game4sell;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.game4sell.Database.DatabaseHelper;
import com.example.game4sell.Model.User;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText emailText;
    EditText passwordText;
    Button loginBtn, registerBtn, getAllBtn;
    public Integer userid;

    Integer passUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        final ProductsFragment product = new ProductsFragment();

        databaseHelper = new DatabaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                User currentUser = databaseHelper.Authenticate(new User(0, email,
                        password, null,null,0));

                if(currentUser !=null){
                    passUserID = databaseHelper.passUserID(new User(0, email, null, null, null, 0));
                    Snackbar.make(loginBtn, "Successfully Login" + passUserID, Snackbar.LENGTH_LONG).show();
                    Intent homeScreen = new Intent(MainActivity.this, NavigationActivity.class);
                    userid = passUserID;
                    homeScreen.putExtra("UserID", passUserID.toString());
                    startActivity(homeScreen);
                }else {
                    //User Logged in Failed
                    Snackbar.make(loginBtn, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        getAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAll();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }

    private void initViews(){
        emailText = findViewById(R.id.userNameEditText);
        passwordText = findViewById(R.id.emailEditText);
        loginBtn = findViewById(R.id.loginButton);
        registerBtn = findViewById(R.id.registerButton);
        getAllBtn = findViewById(R.id.getAllButton);
    }

    private void getAll() {
        Cursor cursor = databaseHelper.getAll();

        if(cursor.getCount() == 0){
            showMessage("Error","Nothing found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append("Id: " + cursor.getString(0) + "\n");
            buffer.append("Email: " + cursor.getString(1) + "\n");
            buffer.append("Password: " + cursor.getString(2) + "\n");
            buffer.append("Username: " + cursor.getString(3) + "\n");
            buffer.append("Address: " + cursor.getString(4) + "\n");
            buffer.append("Number: " + cursor.getInt(5)/100.0 + "\n");
            buffer.append("_________________________" + "\n");

        }
        showMessage("Data", buffer.toString());
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }
}
