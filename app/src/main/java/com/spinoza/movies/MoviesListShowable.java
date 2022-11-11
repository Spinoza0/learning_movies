package com.spinoza.movies;

import androidx.lifecycle.LiveData;

import com.spinoza.movies.movies.Movie;

import java.util.List;

public interface MoviesListShowable {
    LiveData<List<Movie>> getMovies();
    LiveData<Boolean> getIsLoading();
    void loadMovies();
}
