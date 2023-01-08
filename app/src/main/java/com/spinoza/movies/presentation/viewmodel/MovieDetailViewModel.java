package com.spinoza.movies.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spinoza.movies.domain.MovieDao;
import com.spinoza.movies.domain.MoviesApiService;
import com.spinoza.movies.domain.links.Link;
import com.spinoza.movies.domain.movies.Movie;
import com.spinoza.movies.domain.reviews.Review;
import com.spinoza.movies.domain.reviews.ReviewsResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Link>> links = new MutableLiveData<>();
    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private MovieDao movieDao;
    private MoviesApiService apiService;

    public void setInterfaces(@NonNull MovieDao movieDao, @NonNull MoviesApiService apiService) {
        this.movieDao = movieDao;
        this.apiService = apiService;
    }

    public LiveData<Movie> getFavouriteMovie(int id) {
        return movieDao.getFavouriteMovie(id);
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public LiveData<List<Link>> getLinks() {
        return links;
    }

    public LiveData<String> isError() {
        return error;
    }

    public void loadLinks(int id) {
        Disposable disposable = apiService.loadLinks(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(linkResponse -> linkResponse.getLinkItemsList().getItems())
                .subscribe(links::setValue, throwable -> error.setValue(throwable.toString()));
        compositeDisposable.add(disposable);
    }

    public void loadReviews(int id) {
        Disposable disposable = apiService.loadReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ReviewsResponse::getReviews)
                .subscribe(reviews::setValue,
                        throwable -> error.setValue(throwable.toString())
                );

        compositeDisposable.add(disposable);
    }

    public void insertMovie(Movie movie) {
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                }, throwable -> error.setValue(throwable.toString()));
        compositeDisposable.add(disposable);
    }

    public void removeMovie(int movieId) {
        Disposable disposable = movieDao.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                }, throwable -> error.setValue(throwable.toString()));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
