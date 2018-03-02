package com.example.haseeb.loginapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by haseeb on 2/27/2018.
 */

public class Utilz {

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
