package com.spinoza.movies.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("docs")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public MoviesResponse(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MoviesResponse{" +
                "movies=" + movies +
                '}';
    }
}
