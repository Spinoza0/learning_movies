package com.spinoza.movies;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.movies.Movie;
import com.spinoza.movies.movies.MoviesAdapter;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerViewMovies;
    private MoviesList moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        moviesList = new MoviesList(
                this,
                viewModel,
                progressBar,
                recyclerViewMovies,
                new MoviesAdapter.OnMovieClickListener() {
                    @Override
                    public void onMovieClick(Movie movie) {
                        Intent intent = MovieDetailActivity.newIntent(
                                MainActivity.this,
                                movie);
                        startActivity(intent);
                    }
                });
        moviesList.setContent();

    }

    private void initViews() {
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        progressBar = findViewById(R.id.progressBar);
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