package com.vepe.rickapp.data.source.remote.model;

import com.google.gson.annotations.SerializedName;
import com.vepe.rickapp.data.model.CharacterObject;

import java.util.List;

public class CharacterResult {

    public Info getInfo() {
        return info;
    }

    public List<CharacterObject> getCharacters() {
        return characters;
    }

    private Info info;

    @SerializedName("results")
    private List<CharacterObject> characters;
}
