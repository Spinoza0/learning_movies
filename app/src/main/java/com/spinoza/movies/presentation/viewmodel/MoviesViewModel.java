package com.spinoza.movies.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spinoza.movies.domain.MoviesApiService;
import com.spinoza.movies.domain.movies.Movie;
import com.spinoza.movies.presentation.MoviesListShowable;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviesViewModel extends ViewModel implements MoviesListShowable {

    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    private final MutableLiveData<String> error = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MoviesApiService apiService;

    private int page = 1;

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> isError() {
        return error;
    }

    public void setApiService(MoviesApiService apiService) {
        this.apiService = apiService;
    }

    public void loadMovies() {
        Boolean loading = isLoading.getValue();
        if (loading != null && !loading) {
            Disposable disposable = apiService.loadMovies(page)
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
                    }, throwable -> error.setValue(throwable.toString()));
            compositeDisposable.add(disposable);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
