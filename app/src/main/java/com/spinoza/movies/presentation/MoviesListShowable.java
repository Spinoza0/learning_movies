package com.spinoza.movies.presentation;

import androidx.lifecycle.LiveData;

import com.spinoza.movies.domain.movies.Movie;

import java.util.List;

public interface MoviesListShowable {
    LiveData<List<Movie>> getMovies();
    LiveData<Boolean> getIsLoading();
    void loadMovies();
}
