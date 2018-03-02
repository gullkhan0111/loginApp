package com.example.haseeb.loginapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.haseeb.loginapp.Listeners.SearchListener;
import com.example.haseeb.loginapp.R;
import com.example.haseeb.loginapp.activities.UserProfileActivity;
import com.example.haseeb.loginapp.adapters.UserListAdapter;
import com.example.haseeb.loginapp.database.AppDatabase;
import com.example.haseeb.loginapp.database.MyRoomConnection;
import com.example.haseeb.loginapp.dialogs.SearchDialogFragment;
import com.example.haseeb.loginapp.models.User;

import java.util.List;

public class FragmentAllUsers extends Fragment implements SearchListener, UserListAdapter.UserListListener {

    private RecyclerView recyclerView;
    Button showAllUser;
    FloatingActionButton floatingActionButton;
    String name;

    AppDatabase db;
    UserListAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new UserListAdapter(this, getContext());

        db = MyRoomConnection.getDb(getActivity().getBaseContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_box, container, false);
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        //  textValue = view.findViewById(R.id.srhUser);
        showAllUser = view.findViewById(R.id.showAllUser);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getActivity());

        List<User> allUsers = db.userDao().getAll();
        adapter.addData(allUsers);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment searchDialog = new SearchDialogFragment();
                searchDialog.show(getFragmentManager(), "fragment_dialog");
                searchDialog.setTargetFragment(FragmentAllUsers.this, 1);


            }

        });
        showAllUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> allUsers = db.userDao().getAll();
                adapter.addData(allUsers);
            }
        });

    }

    @Override
    public void getDataFromDialog(String name, String cit, String age) {
        System.out.println("sent name is " + name);

        List<User> users = db.userDao().findUsers(name, cit, age);
        adapter.addData(users);


    }

    @Override
    public void onDelete(int id, int index) {
        db.userDao().delete(id);
        adapter.itemDelete(index);
    }

    @Override
    public void onClick(User user) {

        Intent i = new Intent(getActivity(), UserProfileActivity.class);
        i.putExtra("user", user);
        startActivity(i);

    }


}


