package com.spinoza.movies.domain.reviews;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewsResponse {
    @SerializedName("docs")
    private final List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public ReviewsResponse(List<Review> reviews) {
        this.reviews = reviews;
    }
}
