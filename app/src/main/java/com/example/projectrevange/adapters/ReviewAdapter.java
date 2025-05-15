package com.example.projectrevange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectrevange.R;
import com.example.projectrevange.models.Review;
import com.example.projectrevange.screens.ReviewActivity;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    public ReviewAdapter(List<ReviewActivity> reviewActivityList) {
    }

    public interface OnReviewCallback {
        public void onClick(Review review);
    }

    List<Review> reviews;
    Context context;
    ReviewAdapter.OnReviewCallback onReviewCallback;
    public ReviewAdapter(Context context, List<Review> reviews, @Nullable ReviewAdapter.OnReviewCallback onReviewCallback) {
        this.reviews= reviews;
        this.context = context;
        this.onReviewCallback = onReviewCallback;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new ReviewAdapter.ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.item_review_text.setText(review.getText());
        holder.review.setText(review.getUserId());


        holder.itemView.setOnLongClickListener(v -> {
            if (onReviewCallback != null) {
                onReviewCallback.onClick(review);
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }


    static class ReviewViewHolder extends RecyclerView.ViewHolder {
       TextView item_review_text,review;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            item_review_text = itemView.findViewById(R.id.item_review_text);
            review = itemView.findViewById(R.id.review);

        }
    }

}