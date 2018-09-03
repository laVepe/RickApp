package com.vepe.rickapp.domain.characters;

import com.vepe.rickapp.data.Repository;
import com.vepe.rickapp.data.model.CharacterObject;
import com.vepe.rickapp.domain.SingleUseCaseWithParams;

import io.reactivex.Single;

public class GetCharacterDetail extends SingleUseCaseWithParams<CharacterObject, Integer> {

    private Repository repository = Repository.getInstance();

    private GetCharacterDetail() {
    }

    private static GetCharacterDetail instance;

    public static GetCharacterDetail getInstance() {
        if (instance == null) {
            synchronized (GetCharacterDetail.class) {
                if (instance == null) {
                    instance = new GetCharacterDetail();
                }
            }
        }
        return instance;
    }

    @Override
    protected Single<CharacterObject> buildUseCase(Integer id) {
        return repository.getCharacter(id);
    }
}
