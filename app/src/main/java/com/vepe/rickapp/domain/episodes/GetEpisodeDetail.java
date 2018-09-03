package com.vepe.rickapp.domain.episodes;

import com.vepe.rickapp.data.Repository;
import com.vepe.rickapp.data.model.EpisodeObject;
import com.vepe.rickapp.domain.SingleUseCaseWithParams;

import io.reactivex.Single;

public class GetEpisodeDetail extends SingleUseCaseWithParams<EpisodeObject, Integer> {

    private Repository repository = Repository.getInstance();

    private GetEpisodeDetail() {
    }

    private static GetEpisodeDetail instance;

    public static GetEpisodeDetail getInstance() {
        if (instance == null) {
            synchronized (GetEpisodeDetail.class) {
                if (instance == null) {
                    instance = new GetEpisodeDetail();
                }
            }
        }
        return instance;
    }

    @Override
    protected Single<EpisodeObject> buildUseCase(Integer id) {
        return repository.getEpisode(id);
    }
}
