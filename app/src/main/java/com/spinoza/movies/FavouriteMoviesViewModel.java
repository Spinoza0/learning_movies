package com.spinoza.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.spinoza.movies.database.MovieDao;
import com.spinoza.movies.database.MovieDatabase;
import com.spinoza.movies.movies.Movie;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class FavouriteMoviesViewModel extends AndroidViewModel implements MoviesListShowable {
    private static final String TAG = "FavouriteMoviesViewModel";

    private final MovieDao movieDao;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public FavouriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
        loadMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieDao.getAllFavouriteMovies();
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void loadMovies() {
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
