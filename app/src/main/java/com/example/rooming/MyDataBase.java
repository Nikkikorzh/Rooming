package com.example.rooming;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Country.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {
    public abstract CountryDao countryDao();
}
