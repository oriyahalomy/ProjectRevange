package com.example.projectrevange.screens;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectrevange.R;
import com.example.projectrevange.adapters.RevengeAdapter;
import com.example.projectrevange.models.Revenge;
import com.example.projectrevange.services.AuthenticationService;
import com.example.projectrevange.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class EditRevengesManagerActivity extends AppCompatActivity {

    private static final String TAG = "EditRevengesManagerActivity";

    private RecyclerView revengeBusket;
    RevengeAdapter revengeAdapter;
    List<Revenge> revengeList;
    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_revenges);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaseService = DatabaseService.getInstance();

        revengeList = new ArrayList<>();

        revengeAdapter = new RevengeAdapter(revengeList, this, this::showDeleteDialog);
        revengeBusket = findViewById(R.id.revengeList);
        revengeBusket.setLayoutManager(new LinearLayoutManager(this));
        revengeBusket.setAdapter(revengeAdapter);

        databaseService.getRevengeList(new DatabaseService.DatabaseCallback<List<Revenge>>() {
            @Override
            public void onCompleted(List<Revenge> revenges) {
                String userId = AuthenticationService.getInstance().getCurrentUserId();
                Log.i(TAG, revenges.toString());
                for (Revenge revenge : revenges) {
                    if (revenge.getUserIdFrom().equals(userId)) {
                        revengeList.add(revenge);
                    }
                }
                Log.i(TAG, revengeList.toString());
                revengeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });



    }

    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Revenge")
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Revenge revenge = revengeList.get(position);
                        databaseService.deleteRevenge(revenge.getId(), new DatabaseService.DatabaseCallback<Void>() {
                            @Override
                            public void onCompleted(Void object) {
                                // מחיקת הפריט מתוך הרשימה
                                revengeList.remove(position);
                                revengeAdapter.notifyItemRemoved(position);
                                revengeAdapter.notifyDataSetChanged(); // עדכון של ה-RecyclerView
                            }

                            @Override
                            public void onFailed(Exception e) {

                            }
                        });
                    }
                })
                .setNegativeButton("לא", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss(); // סגירת הדיאלוג
                    }
                });

        builder.create().show();
    }


}