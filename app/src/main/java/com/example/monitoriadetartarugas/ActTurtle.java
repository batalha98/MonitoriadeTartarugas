package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.SQLException;
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
import android.widget.Toast;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.ActivitiesController;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.NestController;
import com.example.monitoriadetartarugas.domain.controller.ObservationController;
import com.example.monitoriadetartarugas.domain.controller.SpecieController;
import com.example.monitoriadetartarugas.domain.controller.TurtleController;
import com.example.monitoriadetartarugas.domain.controller.TurtleNestController;
import com.example.monitoriadetartarugas.domain.controller.TurtleNestDataController;
import com.example.monitoriadetartarugas.domain.controller.TurtleTaggsController;
import com.example.monitoriadetartarugas.domain.controller.WindCategoryController;
import com.example.monitoriadetartarugas.domain.controller.WindDirectionController;
import com.example.monitoriadetartarugas.domain.entitys.Observation;
import com.example.monitoriadetartarugas.domain.entitys.Specie;
import com.example.monitoriadetartarugas.domain.entitys.Turtle;
import com.example.monitoriadetartarugas.domain.entitys.TurtleNest;
import com.example.monitoriadetartarugas.domain.entitys.TurtleNestData;
import com.example.monitoriadetartarugas.domain.entitys.TurtleTaggs;
import com.example.monitoriadetartarugas.domain.entitys.WindCategory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActTurtle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private List<Specie> speciesList = new ArrayList<>();
    private long idturtle;
    private Specie specie;
    private String receivedFromTaggs;
    private String[] toSaveTNData, tstnd, str, strings, stringsAux, stringsTaggs, stringsObservation;
    private Spinner spinner_specie;
    private EditText txt_description;

    private int idnest;
    private NestController nestController;
    private TurtleNestController turtleNestController;
    private TurtleController turtleController;
    private SpecieController specieController;
    private BeachController beachController;
    private WindCategoryController windCategoryController;
    private WindDirectionController windDirectionController;
    private ActivitiesController activitiesController;
    private ObservationController observationController;
    private TurtleTaggsController turtleTaggsController;
    private TurtleNestDataController turtleNestDataController;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_turtle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_description = findViewById(R.id.txt_description);
        spinner_specie = findViewById(R.id.spinner_specie);

        spinner_specie.setOnItemSelectedListener(this);

        dataOpenHelper = new DataOpenHelper(this);

        getSpinnerValues();
    }

    private void getSpinnerValues(){
        try {
            connection = dataOpenHelper.getReadableDatabase();
            specieController = new SpecieController(connection);
            speciesList = specieController.fetchAll();
        }catch(SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }

        ArrayAdapter<Specie> specieAdapter = new ArrayAdapter<Specie>(this
                , android.R.layout.simple_spinner_item
                , speciesList);

        specieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_specie.setAdapter(specieAdapter);
    }

    private void confirm() throws ParseException {
        TurtleNestData turtleNestData = new TurtleNestData();
        Observation observation = new Observation();
        TurtleTaggs turtleTaggs = new TurtleTaggs();
        Bundle bundle = getIntent().getExtras();
        Turtle turtle = new Turtle();
        TurtleNest turtleNest = new TurtleNest();
        specie = (Specie) spinner_specie.getSelectedItem();
        String notes = txt_description.getText().toString();
        int idnest;

        connection = dataOpenHelper.getWritableDatabase();
        turtleNestController = new TurtleNestController(connection);
        turtleController = new TurtleController(connection);
        turtleTaggsController = new TurtleTaggsController(connection);
        observationController = new ObservationController(connection);
        turtleNestDataController = new TurtleNestDataController(connection);

        connection = dataOpenHelper.getReadableDatabase();
        nestController = new NestController(connection);
        activitiesController = new ActivitiesController(connection);
        beachController = new BeachController(connection);
        windCategoryController = new WindCategoryController(connection);
        windDirectionController = new WindDirectionController(connection);

        if(bundle!=null){
            receivedFromTaggs = bundle.getString("finalStage");

            str = receivedFromTaggs.split("#");

            strings = str[0].split("@");

            toSaveTNData = str[1].split("!");
            /*
            * toSaveTNData[0] - nestLocalization
            * toSaveTNData[1] - observation
            * toSaveTNData[2] - nest
            * toSaveTNData[3] - hatchling
            * toSaveTNData[4] - taggs
            * */

            stringsAux = strings[0].split("_");

            stringsTaggs = strings[1].split("-");

            stringsObservation = stringsAux[1].split("-");
        }

        turtle.setIdspecie(specie);
        turtle.setNotes(notes);
        idturtle = turtleController.insert(turtle);

        idnest = Integer.parseInt(stringsAux[0]);

        turtleTaggs.setIdturtle(turtleController.fetchOne((int) idturtle));
        turtleTaggs.setLeftring(Integer.parseInt(stringsTaggs[1]));
        turtleTaggs.setRightring(Integer.parseInt(stringsTaggs[2]));
        turtleTaggs.setInternal_tag(Integer.parseInt(stringsTaggs[3]));
        turtleTaggs.setCcl_measure(Double.valueOf(stringsTaggs[4]));
        turtleTaggs.setCwl_measure(Double.valueOf(stringsTaggs[5]));
        turtleTaggs.setDataa(new Date(stringsTaggs[0]));
        turtleTaggsController.insert(turtleTaggs);

        observation.setIdturtle(turtleController.fetchOne((int) idturtle));
        observation.setBeach(
                beachController.fetchOne(stringsObservation[0]));
        observation.setIdactivity(
                activitiesController.fetchOne(Integer.parseInt(stringsObservation[1])));
        observation.setWc(
                windCategoryController.fetchOne(Integer.parseInt(stringsObservation[2])));
        observation.setWd(
                windDirectionController.fetchOne(Integer.parseInt(stringsObservation[3])));
        observation.setDune_height(Float.valueOf(stringsObservation[4]));
        observation.setDataa(new Date(stringsObservation[5]));
        observationController.insert(observation);

        turtleNest.setIdturtle(turtleController.fetchOne((int) idturtle));
        turtleNest.setIdnest(nestController.fetchOne(idnest));
        turtleNestController.insert(turtleNest);

        tstnd = toSaveTNData[0].split("-");

        turtleNestData.setGps_east(Float.parseFloat(tstnd[0]));
        turtleNestData.setGps_south(Float.parseFloat(tstnd[1]));
        turtleNestData.setHabitat(tstnd[2]);
        turtleNestData.setNest_marking_date(new Date(tstnd[3]));

        tstnd = toSaveTNData[1].split("-");

        turtleNestData.setObs_beach(tstnd[0]);
        turtleNestData.setNest_beach(tstnd[0]);
        turtleNestData.setActivity(tstnd[1]);
        turtleNestData.setWc(tstnd[2]);
        turtleNestData.setWd(tstnd[3]);
        turtleNestData.setDune_height(Float.parseFloat(tstnd[4]));
        turtleNestData.setObs_dataa(new Date(tstnd[5]));

        tstnd = toSaveTNData[2].split("-");

        turtleNestData.setDepth(Integer.parseInt(tstnd[1]));
        turtleNestData.setEggs_quantity(Integer.parseInt(tstnd[2]));
        turtleNestData.setDistance_to_tide(Float.parseFloat(tstnd[3]));

        tstnd = toSaveTNData[3].split("-");

        turtleNestData.setHatched(Integer.parseInt(tstnd[0]));
        turtleNestData.setHatch_dataa(new Date(tstnd[1]));
        turtleNestData.setDied_in_nest(Integer.parseInt(tstnd[2]));
        turtleNestData.setAlive_in_nest(Integer.parseInt(tstnd[3]));
        turtleNestData.setUndeveloped(Integer.parseInt(tstnd[4]));
        turtleNestData.setPredated_eggs(Integer.parseInt(tstnd[5]));
        turtleNestData.setUnhatched(Integer.parseInt(tstnd[6]));

        tstnd = toSaveTNData[4].split("-");

        turtleNestData.setTagg_dataa(new Date(tstnd[0]));
        turtleNestData.setLeftring(Integer.parseInt(tstnd[1]));
        turtleNestData.setRightring(Integer.parseInt(tstnd[2]));
        turtleNestData.setInternal_tag(Integer.parseInt(tstnd[3]));
        turtleNestData.setCcl_measure(Double.parseDouble(tstnd[4]));
        turtleNestData.setCwl_measure(Double.parseDouble(tstnd[5]));

        turtleNestData.setIdturtle((int) idturtle);
        turtleNestData.setIdnest(idnest);

        turtleNestDataController.insert(turtleNestData);
    }

    private boolean validateFields(){
        boolean res = false;

        String description = txt_description.getText().toString();

        if(res = isEmptyField(description)){
            txt_description.requestFocus();
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_save_information, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;

            case R.id.action_report:
                if(validateFields() == false) {
                    try {
                        confirm();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(this, "Info added successfully!", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(this, ActListingRecords.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","turtleNest");
                    it.putExtras(bundle);

                    startActivityForResult(it, 0);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
