package com.spinoza.movies.presentation.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.R;

class MovieViewHolder extends RecyclerView.ViewHolder {
    public final ImageView imageViewPoster;
    public final TextView textViewRating;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        textViewRating = itemView.findViewById(R.id.textViewRating);
    }
}