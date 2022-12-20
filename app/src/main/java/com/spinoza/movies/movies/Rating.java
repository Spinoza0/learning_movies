package com.spinoza.movies.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("kp")
    private final double kinopoiskRating;

    public double getKinopoiskRating() {
        return kinopoiskRating;
    }

    public Rating(double kinopoiskRating) {
        this.kinopoiskRating = kinopoiskRating;
    }
}
