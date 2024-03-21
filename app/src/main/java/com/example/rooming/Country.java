package com.example.rooming;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Country {
    @NonNull
    @PrimaryKey
    private String name;
    private String flagId;

    public Country(String name,String flagId){

        this.name=name;
        this.flagId = flagId;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagId() {
        return this.flagId;
    }

    public void setFlagResource(String flagId) {
        this.flagId = flagId;
    }
}
