package com.vepe.rickapp.data.source.remote.model;

import com.google.gson.annotations.SerializedName;

public class Episode {

    public Episode() {
    }

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
