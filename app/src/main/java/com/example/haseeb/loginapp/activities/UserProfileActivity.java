package com.example.haseeb.loginapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.haseeb.loginapp.R;
import com.example.haseeb.loginapp.Utilz;
import com.example.haseeb.loginapp.adapters.UserListAdapter;
import com.example.haseeb.loginapp.database.AppDatabase;
import com.example.haseeb.loginapp.database.MyRoomConnection;
import com.example.haseeb.loginapp.models.User;

public class UserProfileActivity extends AppCompatActivity  {
AppDatabase database;
TextView userNamee,userAge,address;


User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        database = MyRoomConnection.getDb(getApplicationContext());

        userNamee =findViewById(R.id.Name);
        userAge=findViewById(R.id.agge);
        address=findViewById(R.id.city);

        User user = getIntent().getParcelableExtra("user");
        userNamee.setText(user.getUsername());
        userAge.setText(user.getAge());
        address.setText(user.getCityName());





    }


}
