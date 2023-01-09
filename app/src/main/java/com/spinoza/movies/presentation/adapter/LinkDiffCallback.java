package com.spinoza.movies.presentation.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.spinoza.movies.domain.links.Link;

public class LinkDiffCallback extends DiffUtil.ItemCallback<Link> {
    @Override
    public boolean areItemsTheSame(@NonNull Link oldItem, @NonNull Link newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Link oldItem, @NonNull Link newItem) {
        return oldItem.getUrl().equals(newItem.getUrl());
    }
}
