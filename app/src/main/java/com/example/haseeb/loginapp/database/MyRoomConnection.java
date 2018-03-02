package com.example.haseeb.loginapp.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;


public class MyRoomConnection {

    private static AppDatabase instance;

    private MyRoomConnection() {}

    @NonNull
    public static AppDatabase getDb(Context context) {
        if (instance == null) {
            instance = getAppDb(context);
            return instance;
        } else {
            return instance;
        }
    }


    @NonNull
    private static AppDatabase getAppDb(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "login_app_db")
                .allowMainThreadQueries()
                .build();
    }
}


