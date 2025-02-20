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
import com.example.projectrevange.models.User;
import com.example.projectrevange.adapters.UserAdapter;
import com.example.projectrevange.services.AuthenticationService;
import com.example.projectrevange.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class EditUsersManagerActivity extends AppCompatActivity {

    private static final String TAG = "EditUsersManagerActivity";

    private RecyclerView usersBusket;
    UserAdapter UserAdapter;
    List<User> userList;
    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_users_manager);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaseService = DatabaseService.getInstance();

        userList = new ArrayList<>();

        UserAdapter = new UserAdapter(this, userList, this::showDeleteDialog);
        usersBusket = findViewById(R.id.userList);
        usersBusket.setLayoutManager(new LinearLayoutManager(this));
        usersBusket.setAdapter(UserAdapter);

        databaseService.getUserList(new DatabaseService.DatabaseCallback<List<User>>() {
            @Override
            public void onCompleted(List<User> users) {
                String userId = AuthenticationService.getInstance().getCurrentUserId();
                Log.i(TAG, users.toString());
                for (User user : users) {
                    if (user.getUid().equals(userId)) {
                        userList.add(user);
                    }
                }
                Log.i(TAG, userList.toString());
                UserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });



    }

    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove User")
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        User user = userList.get(position);
                        databaseService.deleteUser(user.getUid(), new DatabaseService.DatabaseCallback<Void>() {
                            @Override
                            public void onCompleted(Void object) {
                                // מחיקת הפריט מתוך הרשימה
                                userList.remove(position);
                                UserAdapter.notifyItemRemoved(position);
                                UserAdapter.notifyDataSetChanged(); // עדכון של ה-RecyclerView
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