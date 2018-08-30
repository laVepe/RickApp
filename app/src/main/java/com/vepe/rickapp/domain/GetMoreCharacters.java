package com.vepe.rickapp.domain;

import com.vepe.rickapp.data.Repository;
import com.vepe.rickapp.data.source.remote.model.CharacterResult;

import io.reactivex.Single;

public class GetMoreCharacters extends SingleUseCaseWithParams<CharacterResult, Integer> {

    private Repository repository = Repository.getInstance();

    private GetMoreCharacters() {
    }

    private static GetMoreCharacters instance;

    public static GetMoreCharacters getInstance() {
        if (instance == null) {
            synchronized (GetMoreCharacters.class) {
                if (instance == null) {
                    instance = new GetMoreCharacters();
                }
            }
        }
        return instance;
    }

    @Override
    protected Single<CharacterResult> buildUseCase(Integer page) {
        return repository.getMoreCharacters(page);
    }
}
