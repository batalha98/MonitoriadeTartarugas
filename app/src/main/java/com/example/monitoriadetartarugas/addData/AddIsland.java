package com.example.monitoriadetartarugas.addData;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoriadetartarugas.ListingData;
import com.example.monitoriadetartarugas.R;
import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.IslandController;
import com.example.monitoriadetartarugas.domain.entitys.Beach;
import com.example.monitoriadetartarugas.domain.entitys.Island;

public class AddIsland extends AppCompatActivity {
    private EditText txt_island;
    private Button btn_addIsland;
    private IslandController islandController;
    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;
    private String islandToModify;
    private int pilot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_island);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pilot = 0;
        txt_island = findViewById(R.id.txt_island);
        btn_addIsland = findViewById(R.id.btn_addIsland);

        dataOpenHelper = new DataOpenHelper(this);
        verifyParameters();

        btn_addIsland.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()==false){
                    confirm();
                }
            }
        });
    }

    private void confirm(){
        String str_island = txt_island.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("idBtn","island");
        connection = dataOpenHelper.getWritableDatabase();

        if(pilot==1){
            islandController = new IslandController(connection);
            Island island = new Island();

            island.setIsland(str_island);

            islandController.edit(islandToModify, str_island);

            Intent intent = new Intent(getApplicationContext(), ListingData.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "Island editted successfully!", Toast.LENGTH_LONG).show();
        }else{
            islandController = new IslandController(connection);

            if(islandController.fetchOne(str_island)!=null){
                Toast.makeText(getApplicationContext(), "Island name already exists!", Toast.LENGTH_SHORT).show();
            }else{
                Island new_island = new Island();
                new_island.setIsland(str_island);

                islandController.insert(new_island);

                Intent intent = new Intent(getApplicationContext(), ListingData.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                Toast.makeText(getApplicationContext(), "Island added succesfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyParameters(){
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("island")){
            islandToModify = bundle.getString("island");

            txt_island.setText(islandToModify);

            pilot = 1;
            btn_addIsland.setText("Edit");
        }
    }

    private boolean validateFields(){
        boolean res = false;

        String island = txt_island.getText().toString();

        if(res = isEmptyField(island)){
            txt_island.requestFocus();
        }

        if(res){
            Toast.makeText(getApplicationContext(), "Fill the blank field!", Toast.LENGTH_SHORT).show();
        }

        return res;
    }

    private boolean isEmptyField(String value){
        boolean result = (TextUtils.isEmpty(value) || value.trim().isEmpty());

        return result;
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}