package com.example.projectrevange.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectrevange.R;

public class ManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUsers,btnRevenges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        btnUsers = findViewById(R.id.btnUsers);
        btnRevenges = findViewById(R.id.btnRevenges);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRevenges.setOnClickListener(this);
        btnUsers.setOnClickListener(this);
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

    }
}