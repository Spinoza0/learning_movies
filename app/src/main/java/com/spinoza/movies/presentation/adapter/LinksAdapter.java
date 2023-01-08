package com.spinoza.movies.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.R;
import com.spinoza.movies.domain.links.Link;

import java.util.ArrayList;
import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.LinkViewHolder> {

    private List<Link> links = new ArrayList<>();

    private OnLinkClickListener onLinkClickListener;

    public void setOnLinkClickListener(OnLinkClickListener onLinkClickListener) {
        this.onLinkClickListener = onLinkClickListener;
    }

    public void setLinks(List<Link> links) {
        DiffUtilCallback diffUtilCallback =
                new DiffUtilCallback(this.links, links);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        this.links = links;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.link_item,
                parent,
                false);
        return new LinkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkViewHolder holder, int position) {
        Link link = links.get(position);
        holder.textViewLinkName.setText(link.getName());
        holder.itemView.setOnClickListener(view -> {
            if (onLinkClickListener != null) {
                onLinkClickListener.onLinkClick(link);
            }
        });
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    public interface OnLinkClickListener {
        void onLinkClick(Link link);
    }

    private static class DiffUtilCallback extends DiffUtil.Callback {
        private final List<Link> oldList;
        private final List<Link> newList;

        public DiffUtilCallback(List<Link> oldList, List<Link> newList) {
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

    static class LinkViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewLinkName;

        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLinkName = itemView.findViewById(R.id.textViewName);
        }
    }
}
