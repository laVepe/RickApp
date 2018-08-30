package com.vepe.rickapp.presentation;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable disposables = new CompositeDisposable();

    public void addDisposable(Disposable d) {
        disposables.add(d);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
