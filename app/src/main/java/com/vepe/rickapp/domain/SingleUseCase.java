package com.vepe.rickapp.domain;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public abstract class SingleUseCase<T> {

    protected abstract Single<T> buildUseCase();

    public Single<T> execute() {
        return buildUseCase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
