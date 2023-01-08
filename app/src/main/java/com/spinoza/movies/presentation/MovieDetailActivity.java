package com.spinoza.movies.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.spinoza.movies.data.MovieDatabase;
import com.spinoza.movies.data.MoviesApiFactory;
import com.spinoza.movies.databinding.ActivityMovieDetailBinding;
import com.spinoza.movies.domain.movies.Movie;
import com.spinoza.movies.presentation.adapter.LinksAdapter;
import com.spinoza.movies.presentation.adapter.ReviewsAdapter;
import com.spinoza.movies.presentation.viewmodel.MovieDetailViewModel;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "movie";

    private ActivityMovieDetailBinding binding;
    private MovieDetailViewModel viewModel;

    private LinksAdapter linksAdapter;
    private ReviewsAdapter reviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        viewModel.setInterfaces(
                MovieDatabase.getInstance(getApplication()).movieDao(),
                MoviesApiFactory.apiService
        );

        linksAdapter = new LinksAdapter();
        linksAdapter.setOnLinkClickListener(link -> {
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(link.getUrl())
                    );
                    startActivity(intent);
                }
        );

        reviewsAdapter = new ReviewsAdapter();
        binding.recyclerViewLinks.setAdapter(linksAdapter);
        binding.recyclerViewReviews.setAdapter(reviewsAdapter);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        setContent(movie);
    }

    private void setContent(Movie movie) {
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(binding.imageViewPoster);
        binding.textViewName.setText(movie.getName());
        binding.textViewYear.setText(String.valueOf(movie.getYear()));
        binding.textViewDescription.setText(movie.getDescription());

        viewModel.loadLinks(movie.getId());
        viewModel.getLinks().observe(this, links ->
                linksAdapter.setLinks(links)
        );

        viewModel.getReviews().observe(this,
                reviewItems -> reviewsAdapter.setReviews(reviewItems));
        viewModel.loadReviews(movie.getId());

        Drawable starOff = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.star_big_off);

        Drawable starOn = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.star_big_on);

        viewModel.getFavouriteMovie(movie.getId()).observe(this,
                movieFromDb -> {
                    Drawable star;
                    if (movieFromDb == null) {
                        star = starOff;
                        binding.imageViewStar.setOnClickListener(view ->
                                viewModel.insertMovie(movie)
                        );
                    } else {
                        star = starOn;
                        binding.imageViewStar.setOnClickListener(view ->
                                viewModel.removeMovie(movie.getId())
                        );
                    }
                    binding.imageViewStar.setImageDrawable(star);
                });

        viewModel.isError().observe(
                this,
                error -> Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        );
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}