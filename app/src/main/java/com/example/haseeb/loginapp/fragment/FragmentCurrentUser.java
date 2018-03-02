package com.example.haseeb.loginapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.haseeb.loginapp.R;
import com.example.haseeb.loginapp.activities.LoginActivity;
import com.example.haseeb.loginapp.database.AppDatabase;
import com.example.haseeb.loginapp.database.MyRoomConnection;


@SuppressLint("ValidFragment")
public class FragmentCurrentUser extends Fragment {

    TextView username, address, age;
    Button logout;
    AppDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_current_user, container, false);
        username = (TextView) view.findViewById(R.id.username);
        address = (TextView) view.findViewById(R.id.Address);
        age = view.findViewById(R.id.textwiewAge);
        logout = view.findViewById(R.id.logout);
        //   city =(TextView)view.findViewById(R.id.city);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        String name = preferences.getString("Name", null);


        db = MyRoomConnection.getDb(getContext());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  SharedPreferences settings = context.getSharedPreferences("PreferencesName", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


        if (name.isEmpty()) {
            Log.d("name is null", name);
        } else {
            Log.d("name is  Not null", name);
            username.setText(name);
            String cityName = db.userDao().findCity(name);
            address.setText(cityName.toString());

            String UserAge =db.userDao().findAge(name);
            age.setText(UserAge+" "+"year old");


        }
        return view;

    }



}






