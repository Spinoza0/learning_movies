package com.spinoza.movies.presentation.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.R;

class ReviewViewHolder extends RecyclerView.ViewHolder {
    public final LinearLayout linearLayoutReviews;
    public final TextView textViewAuthor;
    public final TextView textViewTitle;
    public final TextView textViewReview;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        linearLayoutReviews = itemView.findViewById(R.id.linearLayoutReviews);
        textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewReview = itemView.findViewById(R.id.textViewReview);

    }
}