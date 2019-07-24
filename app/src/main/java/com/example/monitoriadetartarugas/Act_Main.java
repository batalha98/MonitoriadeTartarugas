package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Act_Main extends AppCompatActivity {

    private ConstraintLayout layoutContentMain;

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
                registerNestWithouTurtle(v);
            }
        });

        Button button_showData = findViewById(R.id.button_showData);
        button_showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData(v);
            }
        });

        Button button_help = findViewById(R.id.button_help);
        button_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpInfo(v);
            }
        });

        layoutContentMain = (ConstraintLayout) findViewById(R.id.layoutContentMain);
    }

    private void registerTurtleAndNest(View view){
        Intent it = new Intent(Act_Main.this, Act_TurtleAndNest.class);
        startActivityForResult(it, 0);
    }


    private void registerNestWithouTurtle(View view){
        Intent it = new Intent(Act_Main.this, Act_NestWithoutTurtle.class);
        startActivityForResult(it, 0);
    }

    private void showData(View view){
        Intent it = new Intent(Act_Main.this, Act_ShowData.class);
        startActivityForResult(it, 0);
    }

    private void helpInfo(View view){
        Intent it = new Intent(Act_Main.this, Act_Help.class);
        startActivityForResult(it, 0);
    }
}
