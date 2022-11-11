package com.spinoza.movies.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("kp")
    private double kinopoiskRating;

    public double getKinopoiskRating() {
        return kinopoiskRating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kinopoiskRating + '\'' +
                '}';
    }


    public Rating(double kinopoiskRating) {
        this.kinopoiskRating = kinopoiskRating;
    }
}
