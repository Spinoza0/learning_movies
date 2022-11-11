package com.spinoza.movies;

import com.spinoza.movies.links.LinkResponse;
import com.spinoza.movies.movies.MoviesResponse;
import com.spinoza.movies.reviews.ReviewsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String SPINOZA_TOKEN = "S2TJTAC-GC24QA2-P191MTM-RWK2ZP1";

    @GET("movie?token=" + SPINOZA_TOKEN + "&field=rating.kp&search=5-10&" +
            "sortField=votes.kp&sortType=-1&limit=30")
    Single<MoviesResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=" + SPINOZA_TOKEN + "&field=id")
    Single<LinkResponse> loadLinks(@Query("search") int id);

    @GET("review?token=" + SPINOZA_TOKEN + "&field=movieId")
    Single<ReviewsResponse> loadReviews(@Query("search") int id);
}
