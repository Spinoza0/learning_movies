package com.spinoza.movies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.movies.Movie;
import com.spinoza.movies.movies.MoviesAdapter;

public class FavouriteMoviesActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerViewMovies;

    private MoviesList moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movies);
        initViews();

        FavouriteMoviesViewModel viewModel = new ViewModelProvider(this)
                .get(FavouriteMoviesViewModel.class);
        moviesList = new MoviesList(
                this,
                viewModel,
                progressBar,
                recyclerViewMovies,
                new MoviesAdapter.OnMovieClickListener() {
                    @Override
                    public void onMovieClick(Movie movie) {
                        Intent intent = MovieDetailActivity.newIntent(
                                FavouriteMoviesActivity.this,
                                movie);
                        startActivity(intent);
                    }
                }
        );

        moviesList.setContent();

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavouriteMoviesActivity.class);
    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressBar = findViewById(R.id.progressBar);
    }
}