package com.vepe.rickapp.data.model;

import android.support.annotation.Nullable;

import com.vepe.rickapp.data.source.remote.model.Info;

import java.util.List;

public class SuccessModel<T> implements BaseModel {

    public SuccessModel(List<T> data, @Nullable Info info) {
        this.data = data;
        this.info = info;
    }

    public List<T> getData() {
        return data;
    }

    private List<T> data;

    @Nullable
    private Info info;

    @Nullable
    public Info getInfo() {
        return info;
    }
}
