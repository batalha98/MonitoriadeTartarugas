package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ActRecordsMenu extends AppCompatActivity {
    Button button_turtleAndNest;
    Button button_nestWithoutTurtle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_records_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button_turtleAndNest = findViewById(R.id.button_turtleAndNestRecords);
        button_nestWithoutTurtle = findViewById(R.id.button_nestWithoutTurtleRecords);

        button_turtleAndNest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listRecords(v, "turtleNest");
            }
        });

        button_nestWithoutTurtle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listRecords(v, "nestWithoutTurtle");
            }
        });
    }

    private void listRecords(View view, String id){
        Intent it = new Intent(this, ActListingRecords.class);

        Bundle bundle = new Bundle();
        bundle.putString("idBtn",id);
        it.putExtras(bundle);

        startActivityForResult(it, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                Intent intent = new Intent(this, Act_Main.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
