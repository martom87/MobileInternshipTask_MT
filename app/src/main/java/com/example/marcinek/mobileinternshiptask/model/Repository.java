package com.example.marcinek.mobileinternshiptask.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Repository implements Serializable {

    @SerializedName("id")
    private String idRep;

    private String name;

    @SerializedName("full_name")
    private String fullName;

    public Repository() {
    }


    public String getId() {
        return idRep;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }
}
