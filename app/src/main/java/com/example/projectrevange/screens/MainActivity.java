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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAddRevenge,btnLogin,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAddRevenge = findViewById(R.id.btnAddRevenge);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);



        btnAddRevenge.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnAddRevenge.getId()) {
            Intent intent = new Intent(this, AddRevengeActivity.class);
            startActivity(intent);
            return;
        }

        if (v.getId() == btnLogin.getId()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }

        if (v.getId() == btnRegister.getId()) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            return;
        }
    }
}