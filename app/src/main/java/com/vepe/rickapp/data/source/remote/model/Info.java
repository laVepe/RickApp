package com.vepe.rickapp.data.source.remote.model;

import com.google.gson.annotations.SerializedName;

public class Info {

    public Info() {
    }

    private int count;

    private int pages;

    private String next;

    @SerializedName("prev")
    private String previous;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
