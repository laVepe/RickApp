package com.vepe.rickapp.domain.episodes;

import com.vepe.rickapp.data.Repository;
import com.vepe.rickapp.data.source.remote.model.EpisodeResult;
import com.vepe.rickapp.domain.SingleUseCase;

import io.reactivex.Single;

public class GetEpisodes extends SingleUseCase<EpisodeResult> {

    private Repository repository = Repository.getInstance();

    private static GetEpisodes instance;

    private GetEpisodes() {
    }

    public static GetEpisodes getInstance() {
        if (instance == null) {
            synchronized (GetEpisodes.class) {
                if (instance == null) {
                    instance = new GetEpisodes();
                }
            }
        }
        return instance;
    }

    @Override
    protected Single<EpisodeResult> buildUseCase() {
        return repository.getEpisodes();
    }
}
