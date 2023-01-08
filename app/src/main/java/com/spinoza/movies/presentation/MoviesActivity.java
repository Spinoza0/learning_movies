package com.spinoza.movies.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.spinoza.movies.R;
import com.spinoza.movies.data.MoviesApiFactory;
import com.spinoza.movies.databinding.ActivityMoviesBinding;
import com.spinoza.movies.presentation.viewmodel.MoviesViewModel;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMoviesBinding binding = ActivityMoviesBinding
                .inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MoviesViewModel viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);
        viewModel.setApiService(MoviesApiFactory.apiService);
        viewModel.loadMovies();

        new MoviesList(
                this,
                viewModel,
                binding.progressBar,
                binding.recyclerViewMovies,
                movie -> {
                    Intent intent = MovieDetailActivity.newIntent(
                            MoviesActivity.this,
                            movie);
                    startActivity(intent);
                }
        );


        viewModel.isError().observe(
                this,
                error -> Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemFavourite) {
            Intent intent = FavouriteMoviesActivity.newIntent(this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}