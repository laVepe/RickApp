package com.vepe.rickapp.domain;

import com.vepe.rickapp.data.Repository;
import com.vepe.rickapp.data.model.LocationObject;

import java.util.List;

import io.reactivex.Single;

public class GetLocations extends SingleUseCase<List<LocationObject>> {

    private Repository repository = Repository.getInstance();

    private GetLocations() {
    }

    private static GetLocations instance;

    public static GetLocations getInstance() {
        if (instance == null) {
            synchronized (GetLocations.class) {
                if (instance == null) {
                    instance = new GetLocations();
                }
            }
        }
        return instance;
    }

    @Override
    protected Single<List<LocationObject>> buildUseCase() {
        return repository.getLocations();
    }
}
