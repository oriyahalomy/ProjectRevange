package com.example.projectrevange.screens;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectrevange.R;
import com.example.projectrevange.models.Revenge;
import com.example.projectrevange.models.User;
import com.example.projectrevange.models.UserAdapter;
import com.example.projectrevange.services.AuthenticationService;
import com.example.projectrevange.services.DatabaseService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddRevengeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button dateButton;
    private Button saveButton;
    private EditText etReason, etHowRevenge, etTitle;
    private Spinner spinnerUsers;
    private Date selectedDate;
    private DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revenge);

        databaseService = DatabaseService.getInstance();

        // Initialize UI elements
        dateButton = findViewById(R.id.dateButton);
        saveButton = findViewById(R.id.saveButton);
        etReason = findViewById(R.id.etReason);
        spinnerUsers = findViewById(R.id.spinnerUsers);
        etTitle = findViewById(R.id.etTitle);
        etHowRevenge = findViewById(R.id.etHowRevenge);

        // Set onClick listeners
        dateButton.setOnClickListener(v -> {
            if (!isFinishing() && !isDestroyed()) {
                // Get current date
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddRevengeActivity.this,  // Use activity context here
                        (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                            selectedDate = new Date(selectedYear - 1900, selectedMonth, selectedDayOfMonth); // Deprecated Date constructor
                            Toast.makeText(getApplicationContext(), "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
                        },
                        year, month, day);

                // Show the dialog
                datePickerDialog.show();
            }
        });

        loadUsersFromDatabase();

        // Set save and back buttons
        saveButton.setOnClickListener(this);

    }

    private void loadUsersFromDatabase() {
        // Fetch users from the database
        databaseService.getUserList(new DatabaseService.DatabaseCallback<List<User>>() {
            @Override
            public void onCompleted(List<User> users) {
                if (users != null && !users.isEmpty()) {
                    populateUsersSpinner(users);  // Populate the spinner with the users
                } else {
                    // Handle case when no users are available
                    Toast.makeText(AddRevengeActivity.this, "No users available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(Exception e) {
                // Handle failure (e.g., show a Toast or log the error)
                Toast.makeText(AddRevengeActivity.this, "Failed to load users", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateUsersSpinner(List<User> users) {
        // Create the custom adapter for the Spinner
        UserAdapter adapter = new UserAdapter(this, users);
        spinnerUsers.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        // Get data from user inputs
        String reason = etReason.getText().toString();
        User selectedUser = (User) spinnerUsers.getSelectedItem(); // Get selected User object
        String title = etTitle.getText().toString();
        String howRevenge = etHowRevenge.getText().toString();

        if (selectedDate == null) {
            return;
        }

        if (reason.isEmpty()) {
            return;
        }

        // Generate a new ID and create a Revenge object
        String id = databaseService.generateNewRevengeId();
        Revenge revenge = new Revenge(id, title, reason, AuthenticationService.getInstance().getCurrentUserId(),
                selectedUser.getUid(), howRevenge, selectedDate);

        // Save the revenge data to the database
        databaseService.createNewRevenge(revenge, new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void object) {
                finish();  // Close the activity after successful save
            }

            @Override
            public void onFailed(Exception e) {
                // Handle failure here (show a Toast or log the error)
            }
        });



    }
}