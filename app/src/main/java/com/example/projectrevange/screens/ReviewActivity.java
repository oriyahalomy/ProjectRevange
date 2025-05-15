package com.example.projectrevange.screens;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectrevange.R;
import com.example.projectrevange.adapters.RevengeAdapter;
import com.example.projectrevange.adapters.ReviewAdapter;
import com.example.projectrevange.models.Review;
import com.example.projectrevange.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView ReviewList2;

    ReviewAdapter reviewAdapter;
    List<ReviewActivity> reviewActivityList;
    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseService = DatabaseService.getInstance();

        reviewActivityList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(reviewActivityList);

        ReviewList2 = findViewById(R.id.ReviewList);
        ReviewList2.setLayoutManager(new LinearLayoutManager(this));
        ReviewList2.setAdapter(reviewAdapter);

        databaseService.getReviewList(new DatabaseService.DatabaseCallback<List<Review>>() {
            @Override
            public void onCompleted(List<com.example.projectrevange.models.Review> object) {

            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }
}