package com.example.monitoriadetartarugas;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.HabitatController;
import com.example.monitoriadetartarugas.domain.controller.LocalizationAndObservationController;
import com.example.monitoriadetartarugas.domain.controller.NestController;
import com.example.monitoriadetartarugas.domain.controller.NestLocalizationController;
import com.example.monitoriadetartarugas.domain.entitys.Beach;
import com.example.monitoriadetartarugas.domain.entitys.Habitat;
import com.example.monitoriadetartarugas.domain.entitys.LocalizationAndObservation;
import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.NestLocalization;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActNestLocalization extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_habitatName;
    private EditText txt_observationField;
    private EditText dateLocalization;
    private EditText txt_GPSDistance;
    private EditText txt_GPSHeight;
    private EditText txt_observationDate;

    private DatePickerDialog picker;

    private Habitat habitat;
    private String[] strings;
    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    private String dataFromActNest;
    private NestController nestController;
    private NestLocalization nestLocalization;
    private LocalizationAndObservation localizationAndObservation;

    private HabitatController habitatController;
    private BeachController beachController;
    private NestLocalizationController nestLocalizationController;
    private LocalizationAndObservationController localizationAndObservationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nest_localization);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_habitatName = findViewById(R.id.spinner_habitatName);
        txt_observationField = findViewById(R.id.txt_observationField);
        dateLocalization = findViewById(R.id.dateLocalization);
        dateLocalization.setInputType(InputType.TYPE_NULL);
        dateLocalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(calendar.DAY_OF_MONTH);
                int month = calendar.get(calendar.MONTH);
                int year = calendar.get(calendar.YEAR);

                picker = new DatePickerDialog(ActNestLocalization.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateLocalization.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

                picker.show();
            }
        });

        txt_GPSDistance = findViewById(R.id.txt_GPSDistance);
        txt_GPSHeight = findViewById(R.id.txt_GPSHeight);
        txt_observationDate = findViewById(R.id.txt_observationDate);
        txt_observationDate.setInputType(InputType.TYPE_NULL);
        txt_observationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(calendar.DAY_OF_MONTH);
                int month = calendar.get(calendar.MONTH);
                int year = calendar.get(calendar.YEAR);

                picker = new DatePickerDialog(ActNestLocalization.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txt_observationDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

                picker.show();
            }
        });

        spinner_habitatName.setOnItemSelectedListener(this);

        createConnection();
        getSpinnerValues();
    }

    public void createConnection(){
        try {
            dataOpenHelper = new DataOpenHelper(this);

            connection = dataOpenHelper.getWritableDatabase();

            localizationAndObservationController = new LocalizationAndObservationController(connection);
            nestLocalizationController = new NestLocalizationController(connection);
            habitatController = new HabitatController(connection);
            beachController = new BeachController(connection);
            nestController = new NestController(connection);

        }catch(SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    public void getSpinnerValues(){
        List<Habitat> habitats = new ArrayList<>();

        habitats = habitatController.fetchAll();

        ArrayAdapter<Habitat> habitatArrayAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , habitats);

        habitatArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_habitatName.setAdapter(habitatArrayAdapter);
    }

    public void confirm(){
        try {
            strings = null;
            nestLocalization = new NestLocalization();
            localizationAndObservation = new LocalizationAndObservation();
            Bundle bundle = getIntent().getExtras();
            Beach beach = new Beach();
            Nest nest = new Nest();

            String observations = txt_observationField.getText().toString();
            String distance = txt_GPSDistance.getText().toString();
            String height = txt_GPSHeight.getText().toString();
            String date = dateLocalization.getText().toString();
            String obsDate = txt_observationDate.getText().toString();

            if(bundle != null){
                dataFromActNest = bundle.getString("FromActNest");

                strings = dataFromActNest.split("-");

                beach = beachController.fetchOne(strings[0]);
                nest = nestController.fetchOne(Integer.parseInt(strings[2]));
            }

            nestLocalization.setIdnest(nest);
            nestLocalization.setBeach(beach);
            nestLocalization.setIdhabitat(habitatController.fetchOne(habitat.getIdhabitat()));
            nestLocalization.setObservations(observations);
            nestLocalization.setDistance(Float.valueOf(distance));
            nestLocalization.setHeight(Float.valueOf(height));
            nestLocalization.setDataa(new Date(date));

            nestLocalizationController.insert(nestLocalization);

            localizationAndObservation.setIdnest(
                    nestLocalizationController.fetchOne(nest.getIdnest(), nestLocalization.getDataa()));
            localizationAndObservation.setLocalization_date(nestLocalization);
            localizationAndObservation.setObservation_date(new Date(obsDate));

            localizationAndObservationController.insert(localizationAndObservation);
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

        String observations = txt_observationField.getText().toString();
        String distance = txt_GPSDistance.getText().toString();
        String height = txt_GPSHeight.getText().toString();
        String date = dateLocalization.getText().toString();
        String date2 = txt_observationDate.getText().toString();

        if(res = isEmptyField(date)){
            dateLocalization.requestFocus();
        }else
            if(res = isEmptyField(height)){
                txt_GPSHeight.requestFocus();
            }else
                if(res = isEmptyField(distance)){
                    txt_GPSDistance.requestFocus();
                }else
                    if(res = isEmptyField(observations)){
                        txt_observationField.requestFocus();
                    }else
                        if(res = isEmptyField(date2)){
                            txt_observationDate.requestFocus();
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

                    Intent it = new Intent(ActNestLocalization.this, ActHatchling.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("beachTurtleAndIdnest", dataFromActNest);
                    it.putExtras(bundle);

                    startActivityForResult(it, 0);
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        habitat = (Habitat) parent.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}