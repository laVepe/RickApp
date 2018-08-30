package com.vepe.rickapp.data.source.remote.model;

import com.google.gson.annotations.SerializedName;
import com.vepe.rickapp.data.model.LocationObject;

import java.util.List;

public class LocationResult {

    public Info getInfo() {
        return info;
    }

    public List<LocationObject> getLocations() {
        return locations;
    }

    private Info info;

    @SerializedName("results")
    private List<LocationObject> locations;
}
