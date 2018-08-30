package com.vepe.rickapp.presentation;

import android.arch.lifecycle.MutableLiveData;

import com.vepe.rickapp.data.model.BaseModel;
import com.vepe.rickapp.data.model.CharacterObject;
import com.vepe.rickapp.data.model.ErrorModel;
import com.vepe.rickapp.data.model.LoadingModel;
import com.vepe.rickapp.data.model.SuccessModel;
import com.vepe.rickapp.domain.GetCharacterDetail;
import com.vepe.rickapp.domain.GetCharacters;
import com.vepe.rickapp.domain.GetMoreCharacters;

import java.util.Collections;

public class CharactersViewModel extends BaseViewModel {

    public CharactersViewModel() {
        getCharacters = GetCharacters.getInstance();
        getCharacterDetail = GetCharacterDetail.getInstance();
        getMoreCharacters = GetMoreCharacters.getInstance();
    }

    private GetCharacters getCharacters;

    private GetMoreCharacters getMoreCharacters;

    private GetCharacterDetail getCharacterDetail;

    private MutableLiveData<BaseModel> character = new MutableLiveData<>();

    private MutableLiveData<BaseModel> characters = new MutableLiveData<>();

    public MutableLiveData<BaseModel> getCharacter() {
        return character;
    }

    public MutableLiveData<BaseModel> getCharacters() {
        return characters;
    }

    public void loadCharacters() {
        if (characters.getValue() != null) return;
        characters.postValue(new LoadingModel());

        addDisposable(getCharacters.execute()
                .subscribe(
                        data -> characters.postValue(new SuccessModel<CharacterObject>(data.getCharacters(), data.getInfo())),
                        error -> characters.postValue(new ErrorModel(error))
                ));
    }

    public void loadMoreCharacters() {
        int nextPage = Integer.valueOf(((SuccessModel) characters.getValue()).getInfo().getNext().split("page=")[1]);

        addDisposable(getMoreCharacters.execute(nextPage)
                .subscribe(
                data -> characters.postValue(new SuccessModel<CharacterObject>(data.getCharacters(), data.getInfo())),
                error -> characters.postValue(new ErrorModel(error))
        ));
    }

    public void loadCharacterDetail(int id) {
        addDisposable(getCharacterDetail.execute(id)
                .subscribe(
                        data -> character.postValue(new SuccessModel<CharacterObject>(Collections.singletonList(data), null)),
                        error -> character.postValue(new ErrorModel(error))
                ));
    }
}
