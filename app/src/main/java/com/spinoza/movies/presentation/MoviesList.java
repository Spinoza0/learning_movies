package com.spinoza.movies.presentation;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.presentation.adapter.MoviesAdapter;

public class MoviesList {
    public MoviesList(
            Context context,
            MoviesListShowable moviesModel,
            ProgressBar progressBar,
            RecyclerView recyclerViewMovies,
            MoviesAdapter.OnMovieClickListener onMovieClickListener) {

        MoviesAdapter moviesAdapter = new MoviesAdapter();

        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(context, 2));

        moviesAdapter.setOnMovieClickListener(onMovieClickListener);

        moviesModel
                .getMovies()
                .observe((LifecycleOwner) context, moviesAdapter::setMovies);
        moviesModel
                .getIsLoading()
                .observe((LifecycleOwner) context, isLoading ->
                        progressBar.setVisibility(isLoading ?
                                View.VISIBLE : View.GONE));
        moviesAdapter.setOnReachEndListener(moviesModel::loadMovies);
    }
}
