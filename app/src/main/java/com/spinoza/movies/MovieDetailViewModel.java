package com.spinoza.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.spinoza.movies.api.ApiFactory;
import com.spinoza.movies.database.MovieDao;
import com.spinoza.movies.database.MovieDatabase;
import com.spinoza.movies.links.Link;
import com.spinoza.movies.movies.Movie;
import com.spinoza.movies.reviews.Review;
import com.spinoza.movies.reviews.ReviewsResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {
    private static final String TAG = "MovieDetailViewModel";
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Link>> links = new MutableLiveData<>();
    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final MovieDao movieDao;


    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        this.movieDao = MovieDatabase.getInstance(application).movieDao();
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

    public void loadLinks(int id) {
        Disposable disposable = ApiFactory.apiService.loadLinks(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(linkResponse -> linkResponse.getLinkItemsList().getItems())
                .subscribe(links::setValue, throwable -> Log.d(TAG, throwable.toString()));
        compositeDisposable.add(disposable);
    }

    public void loadReviews(int id) {
        Disposable disposable = ApiFactory.apiService.loadReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ReviewsResponse::getReviews)
                .subscribe(reviews::setValue,
                        throwable -> Log.d(TAG, throwable.toString())
                );

        compositeDisposable.add(disposable);
    }

    public void insertMovie(Movie movie) {
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                }, throwable -> Log.d(TAG, throwable.toString()));
        compositeDisposable.add(disposable);
    }

    public void removeMovie(int movieId) {
        Disposable disposable = movieDao.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                }, throwable -> Log.d(TAG, throwable.toString()));
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
