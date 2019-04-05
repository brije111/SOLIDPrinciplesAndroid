package com.example.solidprinciplesandroid.srp;

import android.app.Activity;
import android.content.SharedPreferences;

public class Context extends Activity {

    @Override
    public Object getSystemService(String name) {
        return super.getSystemService(name);
    }

    @Override
    public int checkSelfPermission(String permission) {
        return super.checkSelfPermission(permission);
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(name, mode);
    }
}
