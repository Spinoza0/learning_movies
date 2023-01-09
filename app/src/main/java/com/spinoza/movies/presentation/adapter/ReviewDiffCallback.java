package com.spinoza.movies.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.spinoza.movies.domain.reviews.Review;

public class ReviewDiffCallback extends DiffUtil.ItemCallback<Review> {
    @Override
    public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
        return oldItem.getAuthor().equals(newItem.getAuthor()) &&
                oldItem.getReview().equals(newItem.getReview());
    }
}
