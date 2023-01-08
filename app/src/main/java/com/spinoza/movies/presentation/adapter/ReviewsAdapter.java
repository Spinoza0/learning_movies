package com.spinoza.movies.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.spinoza.movies.R;
import com.spinoza.movies.domain.reviews.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {

    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEGATIVE = "Негативный";

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        DiffUtilCallback diffUtilCallback =
                new DiffUtilCallback(this.reviews, reviews);
        DiffUtil.DiffResult productDiffResult = DiffUtil.calculateDiff(diffUtilCallback);
        this.reviews = reviews;
        productDiffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.review_item,
                        parent,
                        false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewAuthor.setText(review.getAuthor());
        holder.textViewReview.setText(review.getReview());

        String title = review.getTitle();
        if (title == null || title.isEmpty()) {
            holder.textViewTitle.setVisibility(View.GONE);
        } else {
            holder.textViewTitle.setText(title);
        }

        int colorResId;
        switch (review.getType()) {
            case TYPE_POSITIVE:
                colorResId = android.R.color.holo_green_light;
                break;
            case TYPE_NEGATIVE:
                colorResId = android.R.color.holo_red_light;
                break;
            default:
                colorResId = android.R.color.holo_orange_light;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.linearLayoutReviews.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    private static class DiffUtilCallback extends DiffUtil.Callback {
        private final List<Review> oldList;
        private final List<Review> newList;

        public DiffUtilCallback(List<Review> oldList, List<Review> newList) {
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

    static class ReviewViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout linearLayoutReviews;
        private final TextView textViewAuthor;
        private final TextView textViewTitle;
        private final TextView textViewReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutReviews = itemView.findViewById(R.id.linearLayoutReviews);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewReview = itemView.findViewById(R.id.textViewReview);

        }
    }
}
