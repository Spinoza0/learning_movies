package com.spinoza.movies.links;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LinkItemsList {
    @SerializedName("items")
    private final List<Link> items;

    public List<Link> getItems() {
        return items;
    }

    public LinkItemsList(List<Link> items) {
        this.items = items;
    }
}
