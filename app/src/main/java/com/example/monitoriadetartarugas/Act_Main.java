package com.example.monitoriadetartarugas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Act_Main extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sp = getSharedPreferences("user_details", MODE_PRIVATE);
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
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.log_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.action_logout:
                intent = new Intent(Act_Main.this, ActLogin.class);
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
