package com.spinoza.movies.movies;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "favourite_movies")
public class Movie implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;
    @SerializedName("year")
    private final int year;
    @SerializedName("description")
    private final String description;
    @SerializedName("poster")
    @Embedded
    private final Poster poster;
    @SerializedName("rating")
    @Embedded
    private final Rating rating;

    public Movie(int id,
                 int year,
                 String name,
                 String description,
                 Poster poster,
                 Rating rating) {
        this.id = id;
        this.year = year;
        this.name = name;
        this.description = description;
        this.poster = poster;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Poster getPoster() {
        return poster;
    }

    public Rating getRating() {
        return rating;
    }
}
