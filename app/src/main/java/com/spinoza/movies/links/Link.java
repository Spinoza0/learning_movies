package com.spinoza.movies.links;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Link implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

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
