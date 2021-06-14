package com.example.fashakin_loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    //declaring objects to reference the appropriate ones from the xml
    private EditText firstName, lastName, dob, email, password;

    private Button button;
    SharedPreferences userPrefs; //creating the SharedPreference object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userPrefs = getSharedPreferences("UserInfo", MODE_PRIVATE); //assigning the SharedPreferences object

        //assigning the objects I created to their respective xml counterparts
        button = (Button) findViewById(R.id.registration);
        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        dob = (EditText) findViewById(R.id.dob);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.registerpassword);
    }

    //data validation for all user input
    public void registrationHandler(View view) {
        if(firstName.getText().toString().isEmpty() || lastName.getText().toString().isEmpty() || dob.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                || password.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Do not leave fields empty", Toast.LENGTH_LONG).show();
        }
        else if(firstName.getText().toString().length() < 3 || firstName.getText().toString().length() > 30){
            Toast.makeText(getApplicationContext(), "Please Enter a valid First Name", Toast.LENGTH_LONG).show();
        }
        else if(lastName.getText().toString().length() < 3 || lastName.getText().toString().length() > 30){
            Toast.makeText(getApplicationContext(), "Please Enter a valid Last Name", Toast.LENGTH_LONG).show();
        }
        else if(dob.getText().toString().length() < 8 || !dob.getText().toString().contains("/")){
            Toast.makeText(getApplicationContext(), "Please Enter a valid Date of Birth", Toast.LENGTH_LONG).show();
        }
        else if(email.getText().toString().length() < 9 || !email.getText().toString().contains("@") || !email.getText().toString().contains(".")){
            Toast.makeText(getApplicationContext(), "Please Enter a valid Email Address", Toast.LENGTH_LONG).show();
        }
        else if(password.getText().toString().length() < 8 || password.getText().toString() == firstName.getText().toString() || password.getText().toString() == lastName.getText().toString()
                || password.getText().toString() == dob.getText().toString() || password.getText().toString() == email.getText().toString()){
            Toast.makeText(getApplicationContext(), "Please Enter a valid Password", Toast.LENGTH_LONG).show();
        }
        else{
            SharedPreferences.Editor edit = userPrefs.edit(); //creates an editor object to push info to the SharedPreferences object

            //pushing the key/value pairs to the SharedPreferences file
            edit.putString("First Name", firstName.getText().toString());
            edit.putString("Last Name", lastName.getText().toString());
            edit.putString("Date of Birth", dob.getText().toString());
            edit.putString("Email Address", email.getText().toString());
            edit.putString("Password", password.getText().toString());

            edit.apply();//storing the info I just pushed
            Toast.makeText(getApplicationContext(),"Your account has been created.",Toast.LENGTH_LONG).show();

            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();
        }
    }



}