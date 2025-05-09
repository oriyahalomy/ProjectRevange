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
import com.example.projectrevange.adapters.UserRevengeAdapter;
import com.example.projectrevange.models.Revenge;
import com.example.projectrevange.models.User;
import com.example.projectrevange.services.DatabaseService;

import java.util.List;

public class StatManager extends AppCompatActivity {

    DatabaseService databaseService;
    RecyclerView rvStats;
    UserRevengeAdapter userRevengeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stat_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseService = DatabaseService.getInstance();

        rvStats = findViewById(R.id.rv_stats);
        rvStats.setLayoutManager(new LinearLayoutManager(this));
        userRevengeAdapter = new UserRevengeAdapter(this, new UserRevengeAdapter.UserRevengeCallback() {
            @Override
            public void onClick(User user) {

            }
        });

        rvStats.setAdapter(userRevengeAdapter);

        databaseService.getRevengeList(new DatabaseService.DatabaseCallback<List<Revenge>>() {
            @Override
            public void onCompleted(List<Revenge> revenges) {
                userRevengeAdapter.setRevengeList(revenges);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

        databaseService.getUserList(new DatabaseService.DatabaseCallback<List<User>>() {
            @Override
            public void onCompleted(List<User> users) {
                userRevengeAdapter.setUserList(users);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}