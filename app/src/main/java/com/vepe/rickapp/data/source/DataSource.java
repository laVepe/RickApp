package com.vepe.rickapp.data.source;

import com.vepe.rickapp.data.model.CharacterObject;
import com.vepe.rickapp.data.model.EpisodeObject;
import com.vepe.rickapp.data.model.LocationObject;
import com.vepe.rickapp.data.source.remote.model.CharacterResult;
import com.vepe.rickapp.data.source.remote.model.EpisodeResult;

import java.util.List;

import io.reactivex.Single;

public interface DataSource {

    Single<EpisodeResult> getEpisodes();

    Single<EpisodeObject> getEpisode(int id);

    Single<CharacterResult> getCharacters();

    Single<CharacterResult> getMoreCharacters(int page);

    Single<CharacterObject> getCharacter(int id);

    Single<List<LocationObject>> getLocations();

}
