package com.example.rooming;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {
    @Query("SELECT * FROM country")
    List<Country> getAll();

    @Insert
    void insert(Country country);

    @Delete
    void delete(Country country);


}
