package com.vepe.rickapp.data;

import com.vepe.rickapp.data.model.CharacterObject;
import com.vepe.rickapp.data.model.EpisodeObject;
import com.vepe.rickapp.data.model.LocationObject;
import com.vepe.rickapp.data.source.DataSource;
import com.vepe.rickapp.data.source.local.LocalDataSource;
import com.vepe.rickapp.data.source.remote.RemoteDataSource;
import com.vepe.rickapp.data.source.remote.model.CharacterResult;
import com.vepe.rickapp.data.source.remote.model.EpisodeResult;

import java.util.List;

import io.reactivex.Single;

public class Repository implements DataSource {

    private Repository() {
        this.remote = RemoteDataSource.getInstance();
        this.local =  LocalDataSource.getInstance();
    }

    public static Repository getInstance() {
        if (instance == null) {                 // avoid reducing performance with synchronized block
            synchronized (Repository.class) {
                if (instance == null) {
                    instance = new Repository();
                }
            }
        }
        return instance;
    }

    private static Repository instance;

    private RemoteDataSource remote;

    private LocalDataSource local;

    @Override
    public Single<EpisodeResult> getEpisodes() {
        return remote.getEpisodes();
    }

    @Override
    public Single<CharacterResult> getCharacters() {
        return remote.getCharacters();
    }

    @Override
    public Single<CharacterResult> getMoreCharacters(int page) {
        return remote.getMoreCharacters(page);
    }

    @Override
    public Single<CharacterObject> getCharacter(int id) {
        return remote.getCharacter(id);
    }

    @Override
    public Single<List<LocationObject>> getLocations() {
        return remote.getLocations();
    }

    public Single<EpisodeObject> getEpisode(int id) {
        return remote.getEpisode(id);
    }
}
