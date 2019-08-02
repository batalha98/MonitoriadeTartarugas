package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.ActivitiesController;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.ObservationController;
import com.example.monitoriadetartarugas.domain.controller.TurtleActivitiesController;
import com.example.monitoriadetartarugas.domain.controller.TurtleController;
import com.example.monitoriadetartarugas.domain.controller.WindCategoryController;
import com.example.monitoriadetartarugas.domain.controller.WindDirectionController;
import com.example.monitoriadetartarugas.domain.entitys.Activities;
import com.example.monitoriadetartarugas.domain.entitys.Beach;
import com.example.monitoriadetartarugas.domain.entitys.Observation;
import com.example.monitoriadetartarugas.domain.entitys.Turtle;
import com.example.monitoriadetartarugas.domain.entitys.TurtleActivities;
import com.example.monitoriadetartarugas.domain.entitys.WindCategory;
import com.example.monitoriadetartarugas.domain.entitys.WindDirection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActObservation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Turtle turtle;
    private long idturtle;
    private EditText dateObservation;
    private EditText txt_beachTime;
    private EditText txt_BeachHeight;
    private Spinner spinner_wc;
    private Spinner spinner_wd;
    private Spinner spinner_beach;
    private Spinner spinner_activity;

    private List<Beach> beachList = new ArrayList<>();
    private List<Activities> activitiesList = new ArrayList<>();
    private List<WindCategory> windCategoryList = new ArrayList<>();
    private List<WindDirection> windDirectionList = new ArrayList<>();

    private BeachController beachController;
    private TurtleController turtleController;
    private ActivitiesController activitiesController;
    private ObservationController observationController;
    private WindCategoryController windCategoryController;
    private WindDirectionController windDirectionController;
    private TurtleActivitiesController turtleActivitiesController;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_observation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_beach = findViewById(R.id.spinner_beach);
        spinner_activity = findViewById(R.id.spinner_activity);
        spinner_wc = findViewById(R.id.spinner_wc);
        spinner_wd = findViewById(R.id.spinner_wd);
        dateObservation = findViewById(R.id.dateObservation);
        txt_beachTime = findViewById(R.id.txt_beachTime);
        txt_BeachHeight = findViewById(R.id.txt_BeachHeight);

        spinner_wd.setOnItemSelectedListener(this);
        spinner_wc.setOnItemSelectedListener(this);
        spinner_activity.setOnItemSelectedListener(this);
        spinner_beach.setOnItemSelectedListener(this);

        createConnection();
        getSpinnerValues();
    }

    public void getSpinnerValues(){
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

        try{
            Observation observation = new Observation();
            TurtleActivities turtleActivities = new TurtleActivities();
            Bundle bundle = getIntent().getExtras();

            Beach beach = (Beach) spinner_beach.getSelectedItem();
            Activities activities = (Activities) spinner_activity.getSelectedItem();
            WindCategory windCategory = (WindCategory) spinner_wc.getSelectedItem();
            WindDirection windDirection = (WindDirection) spinner_wd.getSelectedItem();
            String dataa = dateObservation.getText().toString();
            String beach_time = txt_beachTime.getText().toString();
            String beach_height = txt_BeachHeight.getText().toString();

            if((bundle != null)){
                turtle = (Turtle) bundle.getSerializable("turtle");

                observation.setIdturtle(turtle);
            }

            observation.setBeach(beachController.fetchOne(beach.getBeach()));
            observation.setWc(windCategoryController.fetchOne(windCategory.getIdwc()));
            observation.setWd(windDirectionController.fetchOne(windDirection.getIdwd()));
            observation.setIdactivity(activitiesController.fetchOne(activities.getIdactivity()));
            observation.setDataa(new Date(dataa));
            observation.setBeach_time(new Date(beach_time));
            observation.setBeach_height(Float.parseFloat(beach_height));

            idturtle = observationController.insert(observation);

            observation = observationController.fetchOne((int)idturtle, beach.getBeach(), new Date(dataa));

            turtleActivities.setIdturtle(observation);
            turtleActivities.setDataa(observation);
            turtleActivities.setBeach(observation);
            turtleActivities.setIdactivity(observation.getIdactivity());

            turtleActivitiesController.insert(turtleActivities);

        }catch (Exception e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    public void createConnection(){
        try {
            dataOpenHelper = new DataOpenHelper(this);

            connection = dataOpenHelper.getWritableDatabase();

            beachController = new BeachController(connection);
            turtleController = new TurtleController(connection);
            activitiesController = new ActivitiesController(connection);
            observationController = new ObservationController(connection);
            windCategoryController = new WindCategoryController(connection);
            windDirectionController = new WindDirectionController(connection);
            turtleActivitiesController = new TurtleActivitiesController(connection);

        }catch(SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_register_turtle_and_nest, menu);

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
                confirm();


                Intent it = new Intent(this, ActNest.class);
//                Bundle parameters = new Bundle();
//                parameters.putSerializable("turtle", turtle);
//                it.putExtras(parameters);
                startActivityForResult(it, 0);
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
