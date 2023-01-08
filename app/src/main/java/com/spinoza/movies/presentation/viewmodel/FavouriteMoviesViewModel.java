package com.spinoza.movies.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spinoza.movies.domain.MovieDao;
import com.spinoza.movies.domain.movies.Movie;
import com.spinoza.movies.presentation.MoviesListShowable;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class FavouriteMoviesViewModel extends ViewModel implements MoviesListShowable {
    private MovieDao movieDao;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public void setMovieDao(@NonNull MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public LiveData<List<Movie>> getMovies() {
        return movieDao.getAllFavouriteMovies();
    }

    @Override
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    @Override
    public void loadMovies() {
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
