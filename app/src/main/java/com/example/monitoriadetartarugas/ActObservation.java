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
import com.example.monitoriadetartarugas.domain.controller.ActivitiesController;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.WindCategoryController;
import com.example.monitoriadetartarugas.domain.controller.WindDirectionController;
import com.example.monitoriadetartarugas.domain.entitys.Activities;
import com.example.monitoriadetartarugas.domain.entitys.Beach;
import com.example.monitoriadetartarugas.domain.entitys.Observation;
import com.example.monitoriadetartarugas.domain.entitys.WindCategory;
import com.example.monitoriadetartarugas.domain.entitys.WindDirection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ActObservation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Beach beach;
    private Activities activities;
    private WindDirection windDirection;
    private WindCategory windCategory;
    private Observation observation;
    private EditText dateObservation;
    private EditText txt_Dune_height;
    private Spinner spinner_wc;
    private Spinner spinner_wd;
    private Spinner spinner_beach;
    private Spinner spinner_activity;

    private String[] strings;
    private String fromActNestLocalization, toTurtleNestData;
    private String toActNest;
    private DatePickerDialog datePicker;

    private List<Beach> beachList = new ArrayList<>();
    private List<Activities> activitiesList = new ArrayList<>();
    private List<WindCategory> windCategoryList = new ArrayList<>();
    private List<WindDirection> windDirectionList = new ArrayList<>();

    private BeachController beachController;
    private ActivitiesController activitiesController;
    private WindCategoryController windCategoryController;
    private WindDirectionController windDirectionController;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_observation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_beach = findViewById(R.id.spinner_beach2);
        spinner_activity = findViewById(R.id.spinner_activity);
        spinner_wc = findViewById(R.id.spinner_wc);
        spinner_wd = findViewById(R.id.spinner_wd);
        dateObservation = findViewById(R.id.dateObservation);
        dateObservation.setInputType(InputType.TYPE_NULL);
        dateObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(calendar.DAY_OF_MONTH);
                int month = calendar.get(calendar.MONTH);
                int year = calendar.get(calendar.YEAR);

                datePicker = new DatePickerDialog(ActObservation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateObservation.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

                datePicker.show();
            }
        });

        txt_Dune_height = findViewById(R.id.txt_DuneHeight);

        spinner_wd.setOnItemSelectedListener(this);
        spinner_wc.setOnItemSelectedListener(this);
        spinner_activity.setOnItemSelectedListener(this);
        spinner_beach.setOnItemSelectedListener(this);

        setSpinnerValues();
    }

    public void setSpinnerValues(){
        try {
            dataOpenHelper = new DataOpenHelper(this);
            connection = dataOpenHelper.getReadableDatabase();

            beachController = new BeachController(connection);
            activitiesController = new ActivitiesController(connection);
            windCategoryController = new WindCategoryController(connection);
            windDirectionController = new WindDirectionController(connection);
        }catch(SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }

        beachList = beachController.fetchAll();
        activitiesList = activitiesController.fetchAll();
        windCategoryList = windCategoryController.fetchAll();
        windDirectionList = windDirectionController.fetchAll();

        ArrayAdapter<Beach> beachArrayAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , beachList);

        ArrayAdapter<Activities> activitiesArrayAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , activitiesList);

        ArrayAdapter<WindCategory> windCategoryArrayAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , windCategoryList);

        ArrayAdapter<WindDirection> windDirectionArrayAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , windDirectionList);

        beachArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activitiesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        windCategoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        windDirectionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_activity.setAdapter(activitiesArrayAdapter);
        spinner_beach.setAdapter(beachArrayAdapter);
        spinner_wc.setAdapter(windCategoryArrayAdapter);
        spinner_wd.setAdapter(windDirectionArrayAdapter);
    }

    public void confirm(){

            observation = new Observation();

            Bundle bundle = getIntent().getExtras();

            beach = (Beach) spinner_beach.getSelectedItem();
            activities = (Activities) spinner_activity.getSelectedItem();
            windCategory = (WindCategory) spinner_wc.getSelectedItem();
            windDirection = (WindDirection) spinner_wd.getSelectedItem();
            String dataa = dateObservation.getText().toString();
            String dune_height = txt_Dune_height.getText().toString();

            if((bundle != null)){
                fromActNestLocalization = bundle.getString("nestlocalizationData");
                strings = fromActNestLocalization.split("!");
                /*
                * strings[0] = nestlocalizationData
                * strings[1] = toTurtleNestData
                * */
            }

            toActNest = beach.getBeach()
                    +"-"+activities.getActivity()
                    +"-"+windCategory.getIdwc()
                    +"-"+windDirection.getIdwd()
                    +"-"+dune_height
                    +"-"+dataa;

            toTurtleNestData = strings[1]+"!"
                    +beach.getBeach()
                    +"-"+activities.getActivity()
                    +"-"+windCategory.getIdwc()
                    +"-"+windDirection.getIdwd()
                    +"-"+dune_height
                    +"-"+dataa;
    }

    public boolean validateFields(){
        boolean res = false;

        String dataa = dateObservation.getText().toString();
        String dune_height = txt_Dune_height.getText().toString();

        if(res = isEmptyField(dataa)){
            dateObservation.requestFocus();
        }else
            if(res = isEmptyField(dune_height)){
                txt_Dune_height.requestFocus();
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
                    Intent it = new Intent(this, ActNest.class);

                    Bundle parameters = new Bundle();
                    parameters.putString("actNestLocalAndObservation",
                            strings[0]+"#"+toActNest+"#"+toTurtleNestData);
                    it.putExtras(parameters);

                    startActivityForResult(it, 0);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
