package com.spinoza.movies.links;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.R;

import java.util.ArrayList;
import java.util.List;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.LinkViewHolder> {

    private List<Link> links = new ArrayList<>();

    private OnLinkClickListener onLinkClickListener;

    public void setOnLinkClickListener(OnLinkClickListener onLinkClickListener) {
        this.onLinkClickListener = onLinkClickListener;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
        notifyDataSetChanged();
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLinkClickListener != null) {
                    onLinkClickListener.onLinkClick(link);
                }
            }
        });
    }

    public interface OnLinkClickListener {
        void onLinkClick(Link link);
    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    static class LinkViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewLinkName;

        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLinkName = itemView.findViewById(R.id.textViewLinkName);
        }
    }
}
