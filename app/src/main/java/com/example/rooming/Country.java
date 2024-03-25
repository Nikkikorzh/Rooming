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

    private String capital;

    private int size;

    public Country(String name,String flagId,String capital,int size){
        this.name=name;
        this.flagId = flagId;
        this.capital = capital;
        this.size = size;
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

    public void setCapital(String capital) {
        this.capital = capital;
    }
    public String getCapital() {
        return this.capital;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void setFlagResource(String flagId) {
        this.flagId = flagId;
    }
}
