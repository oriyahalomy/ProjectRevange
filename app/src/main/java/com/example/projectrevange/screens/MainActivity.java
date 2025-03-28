package com.example.projectrevange.screens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectrevange.R;
import com.example.projectrevange.models.User;
import com.example.projectrevange.services.AuthenticationService;
import com.example.projectrevange.utils.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private Button btnAddRevenge,btnLogin,btnRegister,btnBusket,btnKan11,btnlogOut,btnHelp,btnManager;
     private TextView userLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        btnAddRevenge = findViewById(R.id.btnAddRevenge);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnBusket = findViewById(R.id.btnBusket);
        btnKan11 = findViewById(R.id.btnKan11);
        btnlogOut = findViewById(R.id.btnlogOut);
        userLoggedIn = findViewById(R.id.userLoggedIn);
        btnHelp = findViewById(R.id.btnHelp);
        btnManager = findViewById(R.id.btnManager);





        btnAddRevenge.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnBusket.setOnClickListener(this);
        btnKan11.setOnClickListener(this);
        btnlogOut.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnManager.setOnClickListener(this);



        if(AuthenticationService.getInstance().isUserSignedIn()) {
            btnlogOut.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.GONE);
            btnLogin.setVisibility(View.GONE);
            btnHelp.setVisibility(View.GONE);
            btnBusket.setVisibility(View.VISIBLE);
            btnAddRevenge.setVisibility(View.VISIBLE);
            userLoggedIn.setVisibility(View.VISIBLE);
            User user = SharedPreferencesUtil.getUser(this);
            userLoggedIn.setText(user.getfName());
        }

        if(AuthenticationService.getInstance().isAdmin()) {
            btnManager.setVisibility(View.VISIBLE);
        }

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

        if (v.getId() == btnBusket.getId()) {
            Intent intent = new Intent(this, RevengeBusketActivity.class);
            startActivity(intent);
            return;
        }

        if (v.getId() == btnlogOut.getId()) {
            SharedPreferencesUtil.signOutUser(this);
            AuthenticationService.getInstance().signOut();
            Intent intent = new Intent(this, MainActivity.class);
            /// Clear the back stack (clear history) and start the MainActivity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return;
        }

        btnKan11.setOnClickListener(view -> {
            String url ="https://www.youtube.com/watch?v=iL686rbf82M&list=PLLttfoK87AdW1TI5VZVTlGvQ4LpPAiP-n&index=14";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        if (v.getId() == btnHelp.getId()) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return;
        }

        if (v.getId() == btnManager.getId()) {
            Intent intent = new Intent(this, ManagerActivity.class);
            startActivity(intent);
            return;
        }


    }
}