package com.example.projectrevange.screens;

import android.content.Context;
import android.content.Intent;
import android.graphics.Insets;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectrevange.R;
import com.example.projectrevange.utils.SharedPreferencesUtil;
import com.example.projectrevange.utils.Validator;
import com.example.projectrevange.services.AuthenticationService;
import com.example.projectrevange.services.DatabaseService;
import com.example.projectrevange.models.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText etEmail, etPassword;
    private Button btnLogin;

    private AuthenticationService authenticationService;
    private DatabaseService databaseService;

    private User currentUser;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// set the layout for the activity
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()).toPlatformInsets();
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /// get the instance of the authentication service
        authenticationService = AuthenticationService.getInstance();
        /// get the instance of the database service
        databaseService = DatabaseService.getInstance();

        /// get the views
        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btn_login_login);

        /// set the click listener
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnLogin.getId()) {
            Log.d(TAG, "onClick: Login button clicked");


            /// get the email and password entered by the user
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            /// log the email and password
            Log.d(TAG, "onClick: Email: " + email);
            Log.d(TAG, "onClick: Password: " + password);

            Log.d(TAG, "onClick: Validating input...");
            /// Validate input
            if (!checkInput(email, password)) {
                /// stop if input is invalid
                return;
            }


            /// Login user
            loginUser(email, password);
        }
    }

    /// Method to check if the input is valid
    /// It checks if the email and password are valid
    /// @see Validator#isEmailValid(String)
    /// @see Validator#isPasswordValid(String)
    private boolean checkInput(String email, String password) {
        if (!Validator.isEmailValid(email)) {
            Log.e(TAG, "checkInput: Invalid email address");
            /// show error message to user
            etEmail.setError("Invalid email address");
            /// set focus to email field
            etEmail.requestFocus();
            return false;
        }

        if (!Validator.isPasswordValid(password)) {
            Log.e(TAG, "checkInput: Password must be at least 6 characters long");
            /// show error message to user
            etPassword.setError("Password must be at least 6 characters long");
            /// set focus to password field
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    private void loginUser(String email, String password) {
        authenticationService.signIn(email, password, new AuthenticationService.AuthCallback<>() {
            /// Callback method called when the operation is completed
            /// @param uid the user ID of the user that is logged in
            @Override
            public void onCompleted(String uid) {
                Log.d(TAG, "onCompleted: User logged in successfully");
                /// get the user data from the database
                databaseService.getUser(uid, new DatabaseService.DatabaseCallback<>() {
                    @Override
                    public void onCompleted(User user) {
                        Log.d(TAG, "onCompleted: User data retrieved successfully");
                        /// save the user data to shared preferences
                        SharedPreferencesUtil.saveUser(LoginActivity.this, user);
                        /// Redirect to main activity and clear back stack to prevent user from going back to login screen
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        /// Clear the back stack (clear history) and start the MainActivity
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                    }

                    @Override
                    public void onFailed(Exception e) {
                        Log.e(TAG, "onFailed: Failed to retrieve user data", e);
                        /// Show error message to user
                        etPassword.setError("Invalid email or password");
                        etPassword.requestFocus();
                        /// Sign out the user if failed to retrieve user data
                        /// This is to prevent the user from being logged in again
                        authenticationService.signOut();
                    }
                });
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "onFailed: Failed to log in user", e);
                /// Show error message to user
                etPassword.setError("Invalid email or password");
                etPassword.requestFocus();

            }
        });
    }
}
