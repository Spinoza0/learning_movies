package com.spinoza.movies.links;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Link implements Serializable {
    @SerializedName("name")
    private final String name;
    @SerializedName("url")
    private final String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
