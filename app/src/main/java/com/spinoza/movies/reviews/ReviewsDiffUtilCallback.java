package com.spinoza.movies.reviews;

import androidx.recyclerview.widget.DiffUtil;

import com.spinoza.movies.links.Link;

import java.util.List;

public class ReviewsDiffUtilCallback extends DiffUtil.Callback {
    private final List<Review> oldList;
    private final List<Review> newList;

    public ReviewsDiffUtilCallback(List<Review> oldList, List<Review> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Review oldItem = oldList.get(oldItemPosition);
        Review newItem = newList.get(newItemPosition);
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Review oldItem = oldList.get(oldItemPosition);
        Review newItem = newList.get(newItemPosition);
        return oldItem.getAuthor().equals(newItem.getAuthor()) &&
                oldItem.getReview().equals(newItem.getReview());
    }
}
