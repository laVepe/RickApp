package com.vepe.rickapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EpisodeObject {

    public EpisodeObject() {
    }

    private int id;

    private String name;

    @SerializedName("episode")
    private String episodeCode;

    @SerializedName("air_date")
    private String airDate;

    private String url;

    private String created;

    @SerializedName("characters")
    private List<String> characterUrls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEpisodeCode() {
        return episodeCode;
    }

    public void setEpisodeCode(String episodeCode) {
        this.episodeCode = episodeCode;
    }

    public List<String> getCharacterUrls() {
        return characterUrls;
    }

    public void setCharacterUrls(List<String> characterUrls) {
        this.characterUrls = characterUrls;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }
}
