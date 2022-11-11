package com.spinoza.movies.links;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LinkItemsList {
    @SerializedName("items")
    private List<Link> items;

    @Override
    public String toString() {
        return "LinkItemsList{" +
                "items=" + items +
                '}';
    }

    public List<Link> getItems() {
        return items;
    }

    public LinkItemsList(List<Link> items) {
        this.items = items;
    }
}
