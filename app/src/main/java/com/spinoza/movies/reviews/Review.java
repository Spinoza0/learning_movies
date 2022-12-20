package com.spinoza.movies.reviews;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("title")
    private final String title;
    @SerializedName("type")
    private final String type;
    @SerializedName("review")
    private final String review;
    @SerializedName("author")
    private final String author;

    public Review(String title, String type, String review, String author) {
        this.title = title;
        this.type = type;
        this.review = review;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getReview() {
        return review;
    }

    public String getAuthor() {
        return author;
    }
}
