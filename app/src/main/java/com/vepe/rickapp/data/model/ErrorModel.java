package com.vepe.rickapp.data.model;

public class ErrorModel implements BaseModel {

    private Throwable error;

    public ErrorModel(Throwable error) {
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }
}
