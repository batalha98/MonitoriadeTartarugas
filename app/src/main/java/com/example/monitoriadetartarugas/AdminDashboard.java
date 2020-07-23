package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminDashboard extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp = getSharedPreferences("user_details", MODE_PRIVATE);
        Button btnHabitat = findViewById(R.id.btn_editHabitat);
        Button btnUsers = findViewById(R.id.btn_manageUsers);
        Button btnBeach = findViewById(R.id.btn_editBeach);
        Button btnSpecie = findViewById(R.id.btn_editSpecie);
        Button btnIsland = findViewById(R.id.btn_editIsland);
        Button btnActivities = findViewById(R.id.btn_editActivities);
        Button btnWC = findViewById(R.id.btn_editWC);
        Button btnWD = findViewById(R.id.btn_editWD);

        btnHabitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecords(v,"habitat");
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.log_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.action_logout:
                intent = new Intent(this, ActLogin.class);
                sp = getSharedPreferences("user_details",MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                startActivity(intent);
                break;
            case R.id.action_exit:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}