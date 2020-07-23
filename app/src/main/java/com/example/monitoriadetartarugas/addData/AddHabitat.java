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
import com.example.monitoriadetartarugas.domain.controller.HabitatController;
import com.example.monitoriadetartarugas.domain.controller.SpecieController;
import com.example.monitoriadetartarugas.domain.entitys.Habitat;
import com.example.monitoriadetartarugas.domain.entitys.Specie;

public class AddHabitat extends AppCompatActivity {
    private EditText txt_habitat;
    private Button btn_addHabitat;
    private HabitatController habitatController;
    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;
    private String habitatToModify;
    private int pilot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habitat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pilot = 0;
        txt_habitat = findViewById(R.id.txt_habitat);
        btn_addHabitat = findViewById(R.id.btn_addHabitat);
        dataOpenHelper = new DataOpenHelper(this);
        verifyParameters();

        btn_addHabitat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()==false){
                    confirm();
                }
            }
        });
    }

    private void confirm(){
        String str_habitat = txt_habitat.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("idBtn","habitat");
        connection = dataOpenHelper.getWritableDatabase();

        if(pilot==1){
            habitatController = new HabitatController(connection);
            Habitat habitat = habitatController.fetchOne(habitatToModify);

            habitatController.edit(str_habitat, habitat);

            Intent intent = new Intent(getApplicationContext(), ListingData.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "Habitat editted successfully!", Toast.LENGTH_LONG).show();
        }else{
            habitatController = new HabitatController(connection);

            if(habitatController.fetchOne(str_habitat)!=null){
                Toast.makeText(getApplicationContext(), "Habitat already exists!", Toast.LENGTH_SHORT).show();
            }else{
                Habitat habitat = new Habitat();
                habitat.setHabitat(str_habitat);

                habitatController.insert(habitat);

                Intent intent = new Intent(getApplicationContext(), ListingData.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                Toast.makeText(getApplicationContext(), "Habitat added succesfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyParameters(){
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("habitat")){
            habitatToModify = bundle.getString("habitat");

            txt_habitat.setText(habitatToModify);

            pilot = 1;
            btn_addHabitat.setText("Edit");
        }
    }

    private boolean validateFields(){
        boolean res = false;

        String habitat = txt_habitat.getText().toString();

        if(res = isEmptyField(habitat)){
            txt_habitat.requestFocus();
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