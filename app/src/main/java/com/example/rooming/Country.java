package com.example.rooming;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("cca2")
    public String code;
    public Name name;

    public Flags flags;


    public static class Flags {
        public String  png;
        public String  svg;

        public String  alt;
    }

    public static class Name {
        public String common;
        public String official;
    }

}