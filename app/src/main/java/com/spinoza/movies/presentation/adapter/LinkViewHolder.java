package com.spinoza.movies.presentation.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.R;

class LinkViewHolder extends RecyclerView.ViewHolder {
    public final TextView textViewLinkName;

    public LinkViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewLinkName = itemView.findViewById(R.id.textViewName);
    }
}