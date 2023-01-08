package com.spinoza.movies.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.spinoza.movies.data.MovieDatabase;
import com.spinoza.movies.databinding.ActivityFavouriteMoviesBinding;
import com.spinoza.movies.presentation.viewmodel.FavouriteMoviesViewModel;

public class FavouriteMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFavouriteMoviesBinding binding = ActivityFavouriteMoviesBinding
                .inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FavouriteMoviesViewModel viewModel =
                new ViewModelProvider(this).get(FavouriteMoviesViewModel.class);
        viewModel.setMovieDao(MovieDatabase.getInstance(getApplication()).movieDao());

        new MoviesList(
                this,
                viewModel,
                binding.progressBar,
                binding.recyclerViewMovies,
                movie -> {
                    Intent intent = MovieDetailActivity.newIntent(
                            FavouriteMoviesActivity.this,
                            movie);
                    startActivity(intent);
                }
        );
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavouriteMoviesActivity.class);
    }
}