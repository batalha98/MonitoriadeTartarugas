package com.example.monitoriadetartarugas.addData;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.monitoriadetartarugas.ListingData;
import com.example.monitoriadetartarugas.R;
import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.ActivitiesController;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.IslandController;
import com.example.monitoriadetartarugas.domain.entitys.Activities;
import com.example.monitoriadetartarugas.domain.entitys.Beach;
import com.example.monitoriadetartarugas.domain.entitys.Island;

import java.util.List;

public class AddBeach extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner island_spinner;
    private EditText txt_beach;
    private Button btn_addBeach;
    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;
    private BeachController beachController;
    private IslandController islandController;
    private String beachToModify, selectedIsland;
    private int pilot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beach);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pilot = 0;
        txt_beach = findViewById(R.id.txt_beachName);
        btn_addBeach = findViewById(R.id.btn_addBeach);
        island_spinner = findViewById(R.id.island_spinner);
        island_spinner.setOnItemSelectedListener(this);

        dataOpenHelper = new DataOpenHelper(this);
        setSpinnerValues();
        verifyParameters();

        btn_addBeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()==false){
                    confirm();
                }
            }
        });
    }

    private void setSpinnerValues(){
        try{
            connection = dataOpenHelper.getReadableDatabase();

            islandController = new IslandController(connection);

            List<Island> islands = islandController.fetchAll();

            ArrayAdapter<Island> islandArrayAdapter = new ArrayAdapter<>(this
                    , android.R.layout.simple_spinner_item
                    , islands);

            islandArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            island_spinner.setAdapter(islandArrayAdapter);
        }catch (SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    private void confirm(){
        String str_beach = txt_beach.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("idBtn","beach");
        connection = dataOpenHelper.getWritableDatabase();

        if(pilot==1){
            beachController = new BeachController(connection);
            Beach beach = new Beach();

            beach.setBeach(str_beach);
            beach.setIsland(selectedIsland);

            beachController.edit(beachToModify,beach);

            Intent intent = new Intent(getApplicationContext(), ListingData.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "Beach editted successfully!", Toast.LENGTH_LONG).show();
        }else{
            beachController = new BeachController(connection);

            if(beachController.fetchOne(str_beach)!=null){
                Toast.makeText(getApplicationContext(), "Beach name already exists!", Toast.LENGTH_SHORT).show();
            }else{
                Beach new_beach = new Beach();
                new_beach.setBeach(str_beach);
                new_beach.setIsland(selectedIsland);

                beachController.insert(new_beach);

                Intent intent = new Intent(getApplicationContext(), ListingData.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                Toast.makeText(getApplicationContext(), "Activity added succesfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyParameters(){
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("beach")){
            beachToModify = bundle.getString("beach");

            txt_beach.setText(beachToModify);

            pilot = 1;
            btn_addBeach.setText("Edit");
        }
    }

    private boolean validateFields(){
        boolean res = false;

        String beach = txt_beach.getText().toString();

        if(res = isEmptyField(beach)){
            txt_beach.requestFocus();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Island island = (Island) parent.getSelectedItem();

        selectedIsland = island.getIsland();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}