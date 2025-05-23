package com.example.projectrevange.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import com.example.projectrevange.R;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUsers,btnRevenges,btnReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnUsers = findViewById(R.id.btnUsers);
        btnRevenges = findViewById(R.id.btnRevenges);
        btnReviews = findViewById(R.id.btnReviews);



        btnRevenges.setOnClickListener(this);
        btnUsers.setOnClickListener(this);
        btnReviews.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

        if (v.getId() == btnUsers.getId()) {
            Intent intent = new Intent(this, EditUsersManagerActivity.class);
            startActivity(intent);
            return;
        }

        if (v.getId() == btnRevenges.getId()) {
            Intent intent = new Intent(this, EditRevengesManagerActivity.class);
            startActivity(intent);
            return;
        }

        if (v.getId() == btnReviews.getId()) {
            Intent intent = new Intent(this, ReviewActivity.class);
            startActivity(intent);
            return;
        }


    }
}