package com.vepe.rickapp.data.source.local;

import com.vepe.rickapp.data.model.CharacterObject;
import com.vepe.rickapp.data.model.EpisodeObject;
import com.vepe.rickapp.data.model.LocationObject;
import com.vepe.rickapp.data.source.DataSource;
import com.vepe.rickapp.data.source.remote.model.CharacterResult;
import com.vepe.rickapp.data.source.remote.model.EpisodeResult;

import java.util.List;

import io.reactivex.Single;

public class LocalDataSource implements DataSource {

    private LocalDataSource() {
    }

    public static LocalDataSource getInstance() {
        if (instance == null) {
            synchronized (LocalDataSource.class) {
                if (instance == null) {
                    instance = new LocalDataSource();
                }
            }
        }
        return instance;
    }

    private static LocalDataSource instance;

    @Override
    public Single<EpisodeResult> getEpisodes() {
        return null;
    }

    @Override
    public Single<EpisodeObject> getEpisode(int id) {
        return null;
    }

    @Override
    public Single<CharacterResult> getCharacters() {
        return getMoreCharacters(1);
    }

    @Override
    public Single<CharacterResult> getMoreCharacters(int page) {
        return null;
    }

    @Override
    public Single<CharacterObject> getCharacter(int id) {
        return null;
    }

    @Override
    public Single<List<LocationObject>> getLocations() {
        return null;
    }
}
