package com.example.haseeb.loginapp.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.haseeb.loginapp.R;
import com.example.haseeb.loginapp.Utilz;
import com.example.haseeb.loginapp.database.AppDatabase;
import com.example.haseeb.loginapp.database.MyRoomConnection;
import com.example.haseeb.loginapp.models.User;


public class SignUpActivity extends AppCompatActivity {
    EditText EnterUerName, EnterPassword, EnterCityName, age;
    Button btnSignUp;
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EnterUerName = findViewById(R.id.EnterUsername);
        EnterPassword = findViewById(R.id.Enterpassword);
        EnterCityName = findViewById(R.id.cityname);
        age = findViewById(R.id.age);
        btnSignUp = findViewById(R.id.signUp);

        database = MyRoomConnection.getDb(getApplicationContext());

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataBackToLogin();
                btnSignupp();
            }
        });

    }

    private void btnSignupp() {
        if (EnterUerName.getText().toString().isEmpty()) {
            EnterUerName.setError("Required Field");
            return;
        } else {
            EnterUerName.setError(null);
        }

        if (EnterPassword.getText().toString().isEmpty()) {
            EnterPassword.setError("Required Field");
            return;
        } else {
            EnterPassword.setError(null);
        }

        if (EnterCityName.getText().toString().isEmpty()) {
            EnterCityName.setError("Required Field");
            return;
        } else {
            EnterCityName.setError(null);
        }
        if (age.getText().toString().isEmpty()) {
            EnterCityName.setError("Required Field");
            return;
        } else {
            age.setError(null);
        }


        User newUser = new User();
        try {


            newUser.setUsername(EnterUerName.getText().toString());
            newUser.setPassword(EnterPassword.getText().toString());
            newUser.setCityName(EnterCityName.getText().toString());
            newUser.setAge(age.getText().toString());
            MyRoomConnection.getDb(getApplicationContext()).userDao().insertAll(newUser);
            Utilz.toast(getApplicationContext(), "SignUp Successfully");
            finish();
        }catch (SQLiteConstraintException exception){
            Utilz.toast(getApplicationContext(),"UserName Already Exsist");

        }
    }


    public void sendDataBackToLogin() {
        Intent intent = new Intent();
        intent.putExtra("name", EnterUerName.getText().toString());
        intent.putExtra("password", EnterPassword.getText().toString());
        setResult(RESULT_OK, intent);
    }


}
