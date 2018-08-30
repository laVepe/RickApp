package com.vepe.rickapp.data.source.remote.model;

import com.vepe.rickapp.data.model.CharacterObject;
import com.vepe.rickapp.data.model.EpisodeObject;
import com.vepe.rickapp.data.model.LocationObject;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    static final String EPISODE_ENDPOINT = "episode/";

    static final String CHARACTER_ENDPOINT = "character/";

    static final String LOCATION_ENDPOINT = "location/";

    @GET(CHARACTER_ENDPOINT + "{id}")
    Single<CharacterObject> getCharacter(@Path("id") int id);

    @GET(CHARACTER_ENDPOINT)
    Single<CharacterResult> getCharacters(@Query("page") int page);

    @GET(LOCATION_ENDPOINT)
    Single<List<LocationObject>> getLocations();

    @GET(EPISODE_ENDPOINT)
    Single<EpisodeResult> getEpisodes();

    @GET(EPISODE_ENDPOINT + "{id}")
    Single<EpisodeObject> getEpisode(@Path("id") int id);
}
