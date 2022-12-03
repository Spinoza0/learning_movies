package com.spinoza.movies;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.movies.Movie;
import com.spinoza.movies.movies.MoviesAdapter;

import java.util.List;

public class MoviesList {
    private final Context context;
    private final MoviesListShowable moviesModel;
    private final ProgressBar progressBar;
    private final MoviesAdapter moviesAdapter;

    public MoviesList(
            Context context,
            MoviesListShowable moviesModel,
            ProgressBar progressBar,
            RecyclerView recyclerViewMovies,
            MoviesAdapter.OnMovieClickListener onMovieClickListener) {
        this.context = context;
        this.moviesModel = moviesModel;
        this.progressBar = progressBar;
        moviesAdapter = new MoviesAdapter();

        recyclerViewMovies.setAdapter(moviesAdapter);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(context, 2));

        moviesAdapter.setOnMovieClickListener(onMovieClickListener);
    }

    public void setContent() {
        moviesModel.getMovies().observe((LifecycleOwner) context, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                moviesAdapter.setMovies(movies);
            }
        });

        moviesModel.getIsLoading().observe((LifecycleOwner) context, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        moviesAdapter.setOnReachEndListener(new MoviesAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                moviesModel.loadMovies();
            }
        });
    }

}
