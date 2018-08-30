package com.vepe.rickapp.presentation;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.vepe.rickapp.data.model.BaseModel;
import com.vepe.rickapp.data.model.EpisodeObject;
import com.vepe.rickapp.data.model.ErrorModel;
import com.vepe.rickapp.data.model.LoadingModel;
import com.vepe.rickapp.data.model.SuccessModel;
import com.vepe.rickapp.domain.GetEpisodeDetail;
import com.vepe.rickapp.domain.GetEpisodes;

import java.util.Collections;

public class EpisodesViewModel extends BaseViewModel {

    public EpisodesViewModel() {
        getEpisodes = GetEpisodes.getInstance();
        getEpisodeDetail = GetEpisodeDetail.getInstance();
    }

    private GetEpisodes getEpisodes;

    private GetEpisodeDetail getEpisodeDetail;

    public LiveData<BaseModel> getEpisodes() {
        return episodes;
    }

    public MutableLiveData<BaseModel> getEpisode() {
        return episode;
    }

    private MutableLiveData<BaseModel> episode = new MutableLiveData<>();

    private MutableLiveData<BaseModel> episodes = new MutableLiveData<>();

    public void loadEpisodes() {
        if (episodes.getValue() != null) return;
        episodes.postValue(new LoadingModel());

        addDisposable(getEpisodes.execute()
                .subscribe(
                        data -> episodes.postValue(new SuccessModel<EpisodeObject>(data.getEpisodes(), data.getInfo())),
                        error -> episodes.postValue(new ErrorModel(error))
                ));
    }

    public void loadEpisodeDetail(int id) {
        addDisposable(getEpisodeDetail.execute(id)
                .subscribe(
                        data -> episode.postValue(new SuccessModel<EpisodeObject>(Collections.singletonList(data), null)),
                        error -> episode.postValue(new ErrorModel(error))
                ));
    }
}
