package com.vepe.rickapp.data.source.remote.model;

import com.google.gson.annotations.SerializedName;
import com.vepe.rickapp.data.model.EpisodeObject;

import java.util.List;

public class EpisodeResult {

    public Info getInfo() {
        return info;
    }

    public List<EpisodeObject> getEpisodes() {
        return episodes;
    }

    private Info info;

    @SerializedName("results")
    private List<EpisodeObject> episodes;
}
