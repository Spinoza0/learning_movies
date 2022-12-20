package com.spinoza.movies;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spinoza.movies.links.LinksAdapter;
import com.spinoza.movies.movies.Movie;
import com.spinoza.movies.reviews.ReviewsAdapter;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "movie";

    private MovieDetailViewModel viewModel;

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;
    private RecyclerView recyclerViewLinks;
    private RecyclerView recyclerViewReviews;
    private ImageView imageViewStar;

    private LinksAdapter linksAdapter;
    private ReviewsAdapter reviewsAdapter;

    private Drawable starOff;
    private Drawable starOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        initViews();

        linksAdapter = new LinksAdapter();
        reviewsAdapter = new ReviewsAdapter();
        recyclerViewLinks.setAdapter(linksAdapter);
        recyclerViewReviews.setAdapter(reviewsAdapter);

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        setContent(movie);
        setClickListeners();
    }

    private void setContent(Movie movie) {
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getName());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        viewModel.loadLinks(movie.getId());
        viewModel.getLinks().observe(this, links ->
                linksAdapter.setLinks(links)
        );

        viewModel.getReviews().observe(this,
                reviewItems -> reviewsAdapter.setReviews(reviewItems));
        viewModel.loadReviews(movie.getId());
        viewModel.getFavouriteMovie(movie.getId()).observe(this,
                movieFromDb -> {
                    Drawable star;
                    if (movieFromDb == null) {
                        star = starOff;
                        imageViewStar.setOnClickListener(view -> viewModel.insertMovie(movie));
                    } else {
                        star = starOn;
                        imageViewStar.setOnClickListener(view ->
                                viewModel.removeMovie(movie.getId())
                        );
                    }
                    imageViewStar.setImageDrawable(star);
                });

    }

    void setClickListeners() {
        linksAdapter.setOnLinkClickListener(link -> {
                    Intent intent = new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(link.getUrl())
                    );
                    startActivity(intent);
                }
        );
    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewLinkName);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewLinks = findViewById(R.id.recyclerViewLinks);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        imageViewStar = findViewById(R.id.imageViewStar);

        starOff = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.star_big_off);

        starOn = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.star_big_on);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}