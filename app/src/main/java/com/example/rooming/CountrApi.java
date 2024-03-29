package com.example.rooming;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountrApi {
    @GET("all?fields=cca2,name,flags")
    Call<List<Country>> getAllCountries();
    @GET("alpha/{code}?fields=cca2,name,flags")
    Call<Country> getCountry(@Path("code") String code);
}
