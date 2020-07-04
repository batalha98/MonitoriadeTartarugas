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
import android.widget.EditText;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.HabitatController;
import com.example.monitoriadetartarugas.domain.controller.NestController;
import com.example.monitoriadetartarugas.domain.controller.NestLocalizationController;
import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.NestLocalization;

import java.util.Date;

public class ActNest extends AppCompatActivity {
    private EditText txt_depthField;
    private EditText txt_nrEggsField;
    private EditText txt_nrDistance;
    private EditText txt_descriptionField;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    private long idnest;
    private String[] strings;
    private String[] splitNestLocalization;
    private String[] splitObservation;
    private String receivedFromActObservation;
    private HabitatController habitatController;
    private NestLocalizationController nestLocalizationController;
    private BeachController beachController;
    private NestController nestController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nest);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_depthField = findViewById(R.id.txt_depthField);
        txt_nrEggsField = findViewById(R.id.txt_nrEggsField);
        txt_nrDistance = findViewById(R.id.txt_nrDistance);
        txt_descriptionField = findViewById(R.id.txt_descriptionField);

        createConnection();
    }

    public void createConnection(){
        try {
            dataOpenHelper = new DataOpenHelper(this);

            connection = dataOpenHelper.getWritableDatabase();

            nestController = new NestController(connection);
            nestLocalizationController = new NestLocalizationController(connection);
            habitatController = new HabitatController(connection);
            beachController = new BeachController(connection);
        }catch(SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    public void confirm(){
        try {
            NestLocalization nestLocalization = new NestLocalization();
            Nest nest = new Nest();
            receivedFromActObservation = "";
            Bundle bundle = getIntent().getExtras();
            String depth = txt_depthField.getText().toString();
            String eggs_quantity = txt_nrEggsField.getText().toString();
            String distance = txt_nrDistance.getText().toString();
            String description = txt_descriptionField.getText().toString();

            if(bundle != null){
                receivedFromActObservation = bundle.getString("actNestLocalAndObservation");
                strings = receivedFromActObservation.split("#");

                /*
                * strings[0] = nestlocalization
                * strings[1] = observation
                * */
            }

            splitNestLocalization = strings[0].split("-");
            splitObservation = strings[1].split("-");

            nest.setDepth(Integer.parseInt(depth));
            nest.setNotes(description);
            nest.setDistance_to_tide(Float.valueOf(distance));
            nest.setEggs_quantity(Integer.parseInt(eggs_quantity));

            idnest = nestController.insert(nest);

            nestLocalization.setIdnest(nestController.fetchOne((int) idnest));
            nestLocalization.setGpsEast(Float.valueOf(splitNestLocalization[0]));
            nestLocalization.setGpsSouth(Float.valueOf(splitNestLocalization[1]));
            nestLocalization.setIdhabitat(
                    habitatController.fetchOne(Integer.parseInt(splitNestLocalization[2])));
            nestLocalization.setNotes(splitNestLocalization[3]);
            nestLocalization.setNest_marking_date(new Date(splitNestLocalization[4]));
            nestLocalization.setBeach(beachController.fetchOne(splitObservation[0]));

            nestLocalizationController.insert(nestLocalization);
        }catch (Exception e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    public boolean validateFields(){
        boolean res = false;

        String depth = txt_depthField.getText().toString();
        String eggs_quantity = txt_nrEggsField.getText().toString();
        String distance = txt_nrDistance.getText().toString();
        String description = txt_descriptionField.getText().toString();

        if(res = isEmptyField(depth)){
            txt_depthField.requestFocus();
        }else
            if(res = isEmptyField(eggs_quantity)){
                txt_nrEggsField.requestFocus();
            }else
                if(res = isEmptyField(distance)){
                    txt_nrDistance.requestFocus();
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
                    Intent it = new Intent(this, ActHatchling.class);

                    strings[1] += "#"+idnest;
                    Bundle bundle = new Bundle();
                    bundle.putString("observationFromActNest", strings[1]);
                    it.putExtras(bundle);

                    startActivityForResult(it, 0);
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
