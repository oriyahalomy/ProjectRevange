package com.example.projectrevange.screens;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
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

public class RevengeBusketActivity extends AppCompatActivity {

    private RecyclerView revengeBusket;
    RevengeAdapter revengeAdapter;
    List<Revenge> revengeList;
    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_revenge_busket);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaseService = DatabaseService.getInstance();

        revengeList = new ArrayList<>();

        revengeAdapter = new RevengeAdapter(revengeList, this);
        revengeBusket = findViewById(R.id.RevengeList);
        revengeBusket.setLayoutManager(new LinearLayoutManager(this));
        revengeBusket.setAdapter(revengeAdapter);

        databaseService.getRevengeList(new DatabaseService.DatabaseCallback<List<Revenge>>() {
            @Override
            public void onCompleted(List<Revenge> revenges) {
                String userId = AuthenticationService.getInstance().getCurrentUserId();

                for (Revenge revenge : revenges) {
                    if (revenge.getUserIdFrom().equals(userId)) {
                        revengeList.add(revenge);
                    }
                }
                revengeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });



    }


    private class RevengeBusket {
    }
}