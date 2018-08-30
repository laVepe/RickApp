package com.vepe.rickapp.data.source.remote;

import com.vepe.rickapp.data.model.CharacterObject;
import com.vepe.rickapp.data.model.EpisodeObject;
import com.vepe.rickapp.data.model.LocationObject;
import com.vepe.rickapp.data.source.DataSource;
import com.vepe.rickapp.data.source.remote.model.CharacterResult;
import com.vepe.rickapp.data.source.remote.model.EpisodeResult;
import com.vepe.rickapp.data.source.remote.model.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.vepe.rickapp.data.source.remote.model.Service.EPISODE_ENDPOINT;


public class RemoteDataSource implements DataSource {

    private RemoteDataSource() {
        client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();

        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service.class);
    }

    public static RemoteDataSource getInstance() {
        if (instance == null) {
            synchronized (RemoteDataSource.class) {
                if (instance == null) {
                    instance = new RemoteDataSource();
                }
            }
        }
        return instance;
    }

    private static RemoteDataSource instance;

    private static final String BASE_URL = "https://rickandmortyapi.com/api/";

    private OkHttpClient client;

    private Service service;

    @Override
    public Single<EpisodeResult> getEpisodes() {
        return service.getEpisodes();
    }

    @Override
    public Single<EpisodeObject> getEpisode(int id) {
        return service.getEpisode(id)
                .map(episodeObject -> {
                    List<String> urls = episodeObject.getCharacterUrls();
                    for (String url: urls) {
                        String[] urlParts = url.split(BASE_URL + EPISODE_ENDPOINT);
                        if (urlParts.length > 1) {
                            getCharacter(Integer.valueOf(urlParts[1]));
                        }
                    }
                    return episodeObject;
                });
    }

    @Override
    public Single<CharacterResult> getCharacters() {
        return getMoreCharacters(1);
    }

    @Override
    public Single<CharacterResult> getMoreCharacters(int page) {
        return service.getCharacters(page);
    }

    @Override
    public Single<CharacterObject> getCharacter(int id) {
        return service.getCharacter(id);
    }

    @Override
    public Single<List<LocationObject>> getLocations() {
        return service.getLocations();
    }
}
