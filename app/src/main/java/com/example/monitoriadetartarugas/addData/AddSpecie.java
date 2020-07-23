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
import com.example.monitoriadetartarugas.domain.controller.SpecieController;
import com.example.monitoriadetartarugas.domain.controller.WindDirectionController;
import com.example.monitoriadetartarugas.domain.entitys.Specie;
import com.example.monitoriadetartarugas.domain.entitys.WindDirection;

public class AddSpecie extends AppCompatActivity {
    private EditText txt_specie;
    private Button btn_addSpecie;
    private SpecieController specieController;
    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;
    private String specieToModify;
    private int pilot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_specie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pilot = 0;
        txt_specie = findViewById(R.id.txt_specie);
        btn_addSpecie = findViewById(R.id.btn_addSpecie);
        dataOpenHelper = new DataOpenHelper(this);

        btn_addSpecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()==false){
                    confirm();
                }
            }
        });
    }

    private void confirm(){
        String str_specie = txt_specie.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("idBtn","specie");
        connection = dataOpenHelper.getWritableDatabase();

        if(pilot==1){
            specieController = new SpecieController(connection);
            Specie specie = specieController.fetchOne(specieToModify);

            specieController.edit(str_specie, specie);

            Intent intent = new Intent(getApplicationContext(), ListingData.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "Specie editted successfully!", Toast.LENGTH_LONG).show();
        }else{
            specieController = new SpecieController(connection);

            if(specieController.fetchOne(str_specie)!=null){
                Toast.makeText(getApplicationContext(), "Wind direction already exists!", Toast.LENGTH_SHORT).show();
            }else{
                Specie specie = new Specie();
                specie.setSpecie(str_specie);

                specieController.insert(specie);

                Intent intent = new Intent(getApplicationContext(), ListingData.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                Toast.makeText(getApplicationContext(), "Specie added succesfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyParameters(){
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("specie")){
            specieToModify = bundle.getString("specie");

            txt_specie.setText(specieToModify);

            pilot = 1;
            btn_addSpecie.setText("Edit");
        }
    }

    private boolean validateFields(){
        boolean res = false;

        String specie = txt_specie.getText().toString();

        if(res = isEmptyField(specie)){
            txt_specie.requestFocus();
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