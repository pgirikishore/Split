package com.example.split;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Split extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
