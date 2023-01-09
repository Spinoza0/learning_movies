package com.spinoza.movies.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.spinoza.movies.R;
import com.spinoza.movies.domain.links.Link;

public class LinksAdapter extends ListAdapter<Link, LinkViewHolder> {

    public LinksAdapter() {
        super(new LinkDiffCallback());
    }

    private OnLinkClickListener onLinkClickListener;

    public void setOnLinkClickListener(OnLinkClickListener onLinkClickListener) {
        this.onLinkClickListener = onLinkClickListener;
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
        Link link = getItem(position);
        holder.textViewLinkName.setText(link.getName());
        holder.itemView.setOnClickListener(view -> {
            if (onLinkClickListener != null) {
                onLinkClickListener.onLinkClick(link);
            }
        });
    }

    public interface OnLinkClickListener {
        void onLinkClick(Link link);
    }
}
