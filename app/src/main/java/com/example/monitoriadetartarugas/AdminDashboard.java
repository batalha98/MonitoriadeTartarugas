package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnUsers = findViewById(R.id.btn_manageUsers);
        Button btnBeach = findViewById(R.id.btn_editBeach);
        Button btnSpecie = findViewById(R.id.btn_editSpecie);
        Button btnIsland = findViewById(R.id.btn_editIsland);
        Button btnActivities = findViewById(R.id.btn_editActivities);
        Button btnWC = findViewById(R.id.btn_editWC);
        Button btnWD = findViewById(R.id.btn_editWD);

        btnUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecords(v, "users");
            }
        });

        btnActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecords(v, "activities");
            }
        });

        btnBeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecords(v, "beach");            }
        });

        btnIsland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecords(v, "island");            }
        });

        btnSpecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecords(v, "specie");            }
        });

        btnWC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecords(v, "wc");            }
        });

        btnWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecords(v, "wd");            }
        });
    }

    private void getRecords(View view, String id){
        Intent intent = new Intent(AdminDashboard.this, ListingData.class);
        Bundle bundle = new Bundle();

        bundle.putString("idBtn",id);
        intent.putExtras(bundle);

        startActivityForResult(intent, 0);
    }
}