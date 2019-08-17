package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.NestController;
import com.example.monitoriadetartarugas.domain.controller.NestWithoutTurtleController;
import com.example.monitoriadetartarugas.domain.controller.SpecieController;
import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.NestWithoutTurtle;
import com.example.monitoriadetartarugas.domain.entitys.Specie;

import java.util.List;

public class ActNest2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private long idnest;
    private Spinner spinner_specie2;
    private EditText txt_depthField2;
    private EditText txt_nrEggsField2;
    private EditText txt_nrDistance2;
    private EditText txt_nrWidth2;
    private EditText txt_descriptionField2;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    private Nest nest;
    private Specie specie;
    private NestController nestController;
    private SpecieController specieController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nest_2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_specie2 = findViewById(R.id.spinner_specie2);
        txt_depthField2 = findViewById(R.id.txt_depthField2);
        txt_nrEggsField2 = findViewById(R.id.txt_nrEggsField2);
        txt_nrDistance2 = findViewById(R.id.txt_nrDistance2);
        txt_nrWidth2 = findViewById(R.id.txt_nrWidth2);
        txt_descriptionField2 = findViewById(R.id.txt_descriptionField2);

        spinner_specie2.setOnItemSelectedListener(this);

        createconnection();
        getSpinnerValues();
    }

    public void getSpinnerValues(){
        List<Specie> specieList = specieController.fetchAll();
        ArrayAdapter<Specie> specieArrayAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , specieList);

        specieArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_specie2.setAdapter(specieArrayAdapter);
    }

    public void createconnection(){
        try {
            dataOpenHelper = new DataOpenHelper(this);

            connection = dataOpenHelper.getWritableDatabase();

            specieController = new SpecieController(connection);
            nestController = new NestController(connection);

        }catch (SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    public void confirm(){
        try {
            nest = new Nest();

            String depth = txt_depthField2.getText().toString();
            String eggs_quantity = txt_nrEggsField2.getText().toString();
            String distance = txt_nrDistance2.getText().toString();
            String width = txt_nrWidth2.getText().toString();
            String description = txt_descriptionField2.getText().toString();

            nest.setDepth(Integer.parseInt(depth));
            nest.setDescription(description);
            nest.setDistance(Float.valueOf(distance));
            nest.setEggs_quantity(Integer.parseInt(eggs_quantity));
            nest.setWidth(Float.valueOf(width));

            idnest = nestController.insert(nest);
        }catch(Exception e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    public boolean validateFields(){
        boolean res = false;

        String depth = txt_depthField2.getText().toString();
        String eggs_quantity = txt_nrEggsField2.getText().toString();
        String distance = txt_nrDistance2.getText().toString();
        String width = txt_nrWidth2.getText().toString();
        String description = txt_descriptionField2.getText().toString();

        if(res = isEmptyField(depth)){
            txt_depthField2.requestFocus();
        }else
            if(res = isEmptyField(eggs_quantity)){
                txt_nrEggsField2.requestFocus();
            }else
                if(res = isEmptyField(distance)){
                    txt_nrDistance2.requestFocus();
                }else
                    if(res = isEmptyField(width)){
                        txt_nrWidth2.requestFocus();
                    }else
                        if(res = isEmptyField(description)){
                            txt_descriptionField2.requestFocus();
                        }

        if(res){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            alertDialog.setTitle(R.string.title_aviso);
            alertDialog.setMessage(R.string.camposInvalidos);
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }

        return res;
    }

    private boolean isEmptyField(String value){
        boolean result = (TextUtils.isEmpty(value) || value.trim().isEmpty());

        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_next, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_next:
                if(validateFields() == false){
                    confirm();

                    Intent it = new Intent(this, ActNestLocalization2.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("idnestAndSpecie", idnest+"-"+specie.getIdspecie());
                    it.putExtras(bundle);

                    startActivityForResult(it, 0);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        specie = (Specie) parent.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
