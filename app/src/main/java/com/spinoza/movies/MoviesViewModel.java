package com.spinoza.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.spinoza.movies.api.ApiFactory;
import com.spinoza.movies.movies.Movie;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviesViewModel extends AndroidViewModel implements MoviesListShowable {

    private static final String TAG = "MainViewModel";

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private int page = 1;

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        loadMovies();
    }

    public void loadMovies() {
        Boolean loading = isLoading.getValue();
        if (loading != null && !loading) {
            Disposable disposable = ApiFactory.apiService.loadMovies(page)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable1 -> isLoading.setValue(true))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate(() -> isLoading.setValue(false))
                    .subscribe(moviesResponse -> {
                        List<Movie> loadedMovies = movies.getValue();
                        if (loadedMovies != null) {
                            loadedMovies.addAll(moviesResponse.getMovies());
                            movies.setValue(loadedMovies);
                        } else {
                            movies.setValue(moviesResponse.getMovies());
                        }
                        page++;
                    }, throwable -> Log.e(TAG, throwable.toString()));
            compositeDisposable.add(disposable);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
