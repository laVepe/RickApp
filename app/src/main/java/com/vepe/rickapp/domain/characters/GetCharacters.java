package com.vepe.rickapp.domain.characters;

import com.vepe.rickapp.data.Repository;
import com.vepe.rickapp.data.source.remote.model.CharacterResult;
import com.vepe.rickapp.domain.SingleUseCase;

import io.reactivex.Single;

public class GetCharacters extends SingleUseCase<CharacterResult> {

    private Repository repository = Repository.getInstance();

    private GetCharacters() {
    }

    private static GetCharacters instance;

    public static GetCharacters getInstance() {
        if (instance == null) {
            synchronized (GetCharacters.class) {
                if (instance == null) {
                    instance = new GetCharacters();
                }
            }
        }
        return instance;
    }

    @Override
    protected Single<CharacterResult> buildUseCase() {
        return repository.getCharacters();
    }
}
