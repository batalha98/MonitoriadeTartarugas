package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Act_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button_turtleAndNest = findViewById(R.id.button_turtleAndNest);
        button_turtleAndNest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTurtleAndNest(v);
            }
        });

        Button button_nestWithouTurtle = findViewById(R.id.button_nestWithouTurtle);
        button_nestWithouTurtle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNestWithoutTurtle(v);
            }
        });

        Button button_showData = findViewById(R.id.button_showData);
        button_showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReports(v);
            }
        });

        Button button_help = findViewById(R.id.button_help);
        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help(v);
            }
        });
    }

    private void registerTurtleAndNest(View view){
        Intent it = new Intent(this, ActNestLocalization.class);
        startActivity(it);
    }

    private void registerNestWithoutTurtle(View view){
        Intent it = new Intent(this, ActNestLocalization2.class);
        startActivity(it);
    }

    private void showReports(View view){
        Intent it = new Intent(this, ActRecordsMenu.class);
        startActivity(it);
    }

    private void help(View view){
        Intent it = new Intent(this, ActHelp.class);
        startActivity(it);
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
