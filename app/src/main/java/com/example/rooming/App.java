package com.example.rooming;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instance;

    private  MyDataBase roomDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        roomDataBase = Room.databaseBuilder(this, MyDataBase.class, "database").allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public MyDataBase getDataBase() {
        return roomDataBase;
    }

}
