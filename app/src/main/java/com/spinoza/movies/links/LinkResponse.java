package com.spinoza.movies.links;

import com.google.gson.annotations.SerializedName;

public class LinkResponse {

    @SerializedName("watchability")
    private final LinkItemsList linkItemsList;

    public LinkItemsList getLinkItemsList() {
        return linkItemsList;
    }

    public LinkResponse(LinkItemsList linkItemsList) {
        this.linkItemsList = linkItemsList;
    }
}
