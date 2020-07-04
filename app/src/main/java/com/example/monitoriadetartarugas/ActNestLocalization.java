package com.example.monitoriadetartarugas;

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
    private EditText txt_gpsEast;
    private EditText txt_gpsSouth;

    private DatePickerDialog picker;

    private Habitat habitat;
    private String[] strings;
    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    private String toActObservation;
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

        txt_gpsEast = findViewById(R.id.txt_gpsSouth);
        txt_gpsSouth = findViewById(R.id.txt_gpsEast);

        spinner_habitatName.setOnItemSelectedListener(this);

        dataOpenHelper = new DataOpenHelper(this);

        getSpinnerValues();
    }

    public void getSpinnerValues(){
        try {
            connection = dataOpenHelper.getReadableDatabase();
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
            connection = dataOpenHelper.getWritableDatabase();
            localizationAndObservationController = new LocalizationAndObservationController(connection);
            nestLocalizationController = new NestLocalizationController(connection);

            strings = null;
            nestLocalization = new NestLocalization();
//            Beach beach = new Beach();
//            Nest nest = new Nest();

            String observations = txt_observationField.getText().toString();
            String date = dateLocalization.getText().toString();
            Float gpsEast = Float.valueOf(txt_gpsEast.getText().toString());
            Float gpsSouth = Float.valueOf(txt_gpsSouth.getText().toString());
            Date date1 = new Date(date);

            toActObservation = gpsEast+"-"+
                    gpsSouth+"-"+habitat.getIdhabitat()+"-"+
                    observations+"-"+date1;

            //nestLocalizationController.insert(nestLocalization);
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

        String distance = txt_gpsEast.getText().toString();
        String height = txt_gpsSouth.getText().toString();
        String date = dateLocalization.getText().toString();

        if(res = isEmptyField(date)){
            dateLocalization.requestFocus();
        }else
            if(res = isEmptyField(height)){
                txt_gpsSouth.requestFocus();
            }else
                if(res = isEmptyField(distance)){
                    txt_gpsEast.requestFocus();
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

                    Intent it = new Intent(this, ActObservation.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("nestlocalizationData", toActObservation);
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
