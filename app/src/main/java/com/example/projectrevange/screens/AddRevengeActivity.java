package com.example.projectrevange.screens;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectrevange.R;
import com.example.projectrevange.models.Revenge;
import com.example.projectrevange.services.AuthenticationService;
import com.example.projectrevange.services.DatabaseService;

import java.util.Calendar;
import java.util.Date;

public class AddRevengeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button dateButton,btnBack;
    private Button saveButton;
    private EditText etReason,etHowRevenge,etTitle;
    private Spinner spinnerUsers;
    Date selectedDate;
    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revenge);

        databaseService = DatabaseService.getInstance();

        dateButton = findViewById(R.id.dateButton);
        btnBack = findViewById(R.id.btnBack);


        dateButton.setOnClickListener(v -> {
            // קבלת תאריך נוכחי
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // יצירת DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                getApplicationContext(),
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                        // הצגת התאריך שנבחר
                    selectedDate = new Date(selectedYear, selectedMonth, selectedDayOfMonth);
                    Toast.makeText(getApplicationContext(), "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
                },
                year, month, day);

                // הצגת הדיאלוג
                datePickerDialog.show();
            });

        etReason = findViewById(R.id.etReason);
        spinnerUsers= findViewById(R.id.spinnerUsers);
        saveButton = findViewById(R.id.saveButton);
        etTitle = findViewById(R.id.etTitle);
        etHowRevenge = findViewById(R.id.etHowRevenge);


        // הגדרת פעולה לכפתור השמירה
        saveButton.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // קבלת נתונים מהמשתמש
        String reason = etReason.getText().toString();
        String selectedUser = spinnerUsers.getSelectedItem().toString(); //TODO
        String title =  etTitle.getText().toString();
        String howRevenge = etHowRevenge.getText().toString();
        if (selectedDate == null) {
            return;
        }
        if (reason.isEmpty()) {
            return;
        }
        String id = databaseService.generateNewRevengeId();
        // שמירה בבסיס הנתונים
        Revenge revenge = new Revenge(id, title, reason, AuthenticationService.getInstance().getCurrentUserId(),
                selectedUser, howRevenge, selectedDate);
        databaseService.createNewRevenge(revenge, new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void object) {
                finish();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

        if (v.getId() == btnBack.getId()) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return;
        }

    }
}