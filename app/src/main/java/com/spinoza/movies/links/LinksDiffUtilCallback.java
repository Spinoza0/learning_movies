package com.spinoza.movies.links;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class LinksDiffUtilCallback extends DiffUtil.Callback {
    private final List<Link> oldList;
    private final List<Link> newList;

    public LinksDiffUtilCallback(List<Link> oldList, List<Link> newList) {
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
        Link oldItem = oldList.get(oldItemPosition);
        Link newItem = newList.get(newItemPosition);
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Link oldItem = oldList.get(oldItemPosition);
        Link newItem = newList.get(newItemPosition);
        return oldItem.getUrl().equals(newItem.getUrl());
    }
}
