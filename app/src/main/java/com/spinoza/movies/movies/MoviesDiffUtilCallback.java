package com.spinoza.movies.movies;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MoviesDiffUtilCallback extends DiffUtil.Callback {
    private final List<Movie> oldList;
    private final List<Movie> newList;

    public MoviesDiffUtilCallback(List<Movie> oldList, List<Movie> newList) {
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
        Movie oldItem = oldList.get(oldItemPosition);
        Movie newItem = newList.get(newItemPosition);
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Movie oldItem = oldList.get(oldItemPosition);
        Movie newItem = newList.get(newItemPosition);
        return oldItem.getYear() == newItem.getYear() &&
                oldItem.getName().equals(newItem.getName()) &&
                oldItem.getDescription().equals(newItem.getDescription());
    }
}
