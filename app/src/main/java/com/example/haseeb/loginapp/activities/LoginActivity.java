package com.example.haseeb.loginapp.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haseeb.loginapp.R;
import com.example.haseeb.loginapp.Utilz;
import com.example.haseeb.loginapp.database.AppDatabase;
import com.example.haseeb.loginapp.database.MyRoomConnection;
import com.example.haseeb.loginapp.fragment.FragmentAllUsers;
import com.example.haseeb.loginapp.models.User;

import java.util.List;


public class LoginActivity extends AppCompatActivity {
    public EditText username, password;
    TextView SignUp,Rigester;
    Button logIn;
    String name;
    String key;
FragmentAllUsers fragmentAllUsers ;
    CheckBox RemembarMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.logIn);
        RemembarMe = findViewById(R.id.saveLoginCheckBox);
        SignUp = findViewById(R.id.Rigester);


        logIn.setOnClickListener(new View.OnClickListener() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
            SharedPreferences.Editor editor = preferences.edit();

            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                   Utilz.toast(getApplicationContext(),"Enter UserName /password");
                } else {
                    if (RemembarMe.isChecked()) {
                        //  ToastMessage("cheakBoc is clcikd");
                        editor.putBoolean("save", true);
                        editor.apply();
                        editor.commit();

                    }

                    name = username.getText().toString();
                    key = password.getText().toString();
                    checkUser(name, key);
                    editor.putString("Name", name);
                    editor.putString("password", key);
                    editor.apply();
                    editor.commit();

                }

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(i, 1);
            }
        });

    }


    protected void onStart() {
        super.onStart();
        SharedPreferences prefes = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Boolean yourLocked = prefes.getBoolean("save", false);
        System.out.println("boolean value is " + yourLocked);
        if (yourLocked == true) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivityForResult(intent, 1);
        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (resultCode == RESULT_OK && requestCode == 1) {
            String message = data.getStringExtra("name");
            username.setText(message);
            String pass = data.getStringExtra("password");
            username.setText(message);
            password.setText(pass);
        }
    }

    private void checkUser(String name, String pass) {


        AppDatabase db = MyRoomConnection.getDb(getApplicationContext());
        List<User> users = db.userDao().Authentication(name, pass);

        if (users.isEmpty()) {

            Toast.makeText(this, "Wrong pass/userName", Toast.LENGTH_SHORT).show();

        } else {

            Utilz.toast(this, "Success");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }



}