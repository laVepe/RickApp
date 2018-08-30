package com.vepe.rickapp.domain;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class SingleUseCaseWithParams<T, Params> {

    protected abstract Single<T> buildUseCase(Params params);

    public Single<T> execute(Params params) {
        return buildUseCase(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
