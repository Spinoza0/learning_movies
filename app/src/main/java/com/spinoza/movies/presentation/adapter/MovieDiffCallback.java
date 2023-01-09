package com.spinoza.movies.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.spinoza.movies.domain.movies.Movie;

public class MovieDiffCallback extends DiffUtil.ItemCallback<Movie> {
    @Override
    public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
        return oldItem.getYear() == newItem.getYear() &&
                oldItem.getName().equals(newItem.getName()) &&
                oldItem.getDescription().equals(newItem.getDescription());
    }
}
