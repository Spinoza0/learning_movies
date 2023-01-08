package com.spinoza.movies.domain.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("docs")
    private final List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public MoviesResponse(List<Movie> movies) {
        this.movies = movies;
    }
}
