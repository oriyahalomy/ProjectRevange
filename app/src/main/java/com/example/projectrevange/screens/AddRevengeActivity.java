package com.example.projectrevange.screens;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectrevange.R;
import com.example.projectrevange.models.Revenge;
import com.example.projectrevange.services.DatabaseService;

import java.util.Calendar;
import java.util.Date;

public class AddRevengeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button dateButton;
    private Button saveButton;
    private EditText etReason;
    private Spinner spinnerUsers;
    Date selectedDate;
    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revenge);

        databaseService = DatabaseService.getInstance();

        dateButton = findViewById(R.id.dateButton);

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

        // הגדרת פעולה לכפתור השמירה
        saveButton.setOnClickListener(this)
    }

    @Override
    public void onClick(View v) {
        // קבלת נתונים מהמשתמש
        String reason = etReason.getText().toString();
        String selectedUser = spinnerUsers.getSelectedItem().toString();
        if (selectedDate == null) {

            return;
        }
        if (!reason.isEmpty()) {
            databaseService.generateNewRevengeId()
            // שמירה בבסיס הנתונים
            Revenge revenge = new Revenge()
            databaseService.c
            finish();
        } else {
            Toast.makeText(AddRevengeActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
        }
    }
}




}