package com.example.fashakin_loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences userPrefs; //creating the SharedPreference object
    private String userE, userP; //string variables for stored username and password
    private Button regBtn, loginBtn;
    private EditText userEmail, userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //declaring and assigning EditText objects
        userEmail = (EditText) findViewById(R.id.username);
        userPassword = (EditText) findViewById(R.id.password);
        regBtn = (Button) findViewById(R.id.register);
        loginBtn = (Button) findViewById(R.id.login);

        userPrefs = getSharedPreferences("UserInfo", MODE_PRIVATE); //assigning the SharedPreferences object

        userE = userPrefs.getString("Email Address", "");
        userP = userPrefs.getString("Password", "");

    }

    public void signInHandler(View view) {
        if(userEmail.getText().toString().isEmpty() || userPassword.getText().toString().isEmpty() || userE.equals("") || userP.equals("")){
            Toast.makeText(getApplicationContext(), "Invalid Login Info.", Toast.LENGTH_LONG).show();
        }
        else if(userEmail.getText().toString().equals(userE) && userPassword.getText().toString().equals(userP)) {
            Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Please create an account.", Toast.LENGTH_LONG).show();
        }
    }

    public void newUserHandler(View view) {
        startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
        finish();
    }
}