package com.example.projectrevange.screens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectrevange.R;
import com.example.projectrevange.services.AuthenticationService;
import com.example.projectrevange.utils.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     private Button btnAddRevenge,btnLogin,btnRegister,btnBusket,btn11,btnlogOut;
     private textView UserLoggedIn;

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
        btnBusket = findViewById(R.id.btnBusket);
        btn11 = findViewById(R.id.btn11);
        btnlogOut = findViewById(R.id.btnlogOut);
        UserLoggedIn = findViewById(R.id.UserLoggedin);




        btnAddRevenge.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnBusket.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btnlogOut.setOnClickListener(this);

        if(AuthenticationService.getInstance().isUserSignedIn()) {
            btnlogOut.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.GONE);
            btnLogin.setVisibility(View.GONE);
            UserLoggedIn.setVisibility(View.VISIBLE);
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
    }
}