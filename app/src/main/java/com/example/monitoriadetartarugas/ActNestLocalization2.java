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
import com.example.monitoriadetartarugas.domain.entitys.NestLocalization;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActNestLocalization2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_beach2;
    private Spinner spinner_habitat2;
    private EditText dateLocalization2;
    private EditText txt_gpsEast2;
    private EditText txt_gpsSouth2;
    private EditText txt_observationField2;
    private DatePickerDialog picker;

    private String toActNest, toNestWTurtleData;
    private Beach beach;
    private Habitat habitat;
    private BeachController beachController;
    private HabitatController habitatController;

    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nest_localization_2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_habitat2 = findViewById(R.id.spinner_habitat2);
        spinner_beach2 = findViewById(R.id.spinner_beach2);
        dateLocalization2 = findViewById(R.id.dateLocalization2);
        dateLocalization2.setInputType(InputType.TYPE_NULL);
        dateLocalization2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(calendar.DAY_OF_MONTH);
                int month = calendar.get(calendar.MONTH);
                int year = calendar.get(calendar.YEAR);

                picker = new DatePickerDialog(ActNestLocalization2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateLocalization2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

                picker.show();
            }
        });

        txt_gpsEast2 = findViewById(R.id.txt_gpsSouth2);
        txt_gpsSouth2 = findViewById(R.id.txt_gpsEast2);
        txt_observationField2 = findViewById(R.id.txt_observationField2);
        spinner_habitat2.setOnItemSelectedListener(this);
        spinner_beach2.setOnItemSelectedListener(this);

        dataOpenHelper = new DataOpenHelper(this);

        getSpinnerValues();
    }

    public void getSpinnerValues(){
        try {
            connection = dataOpenHelper.getReadableDatabase();
            beachController = new BeachController(connection);
            habitatController = new HabitatController(connection);
        }catch(SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }

        List<Beach> beachList = beachController.fetchAll();
        List<Habitat> habitats = habitatController.fetchAll();

        ArrayAdapter<Beach> beachArrayAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , beachList);

        ArrayAdapter<Habitat> habitatArrayAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , habitats);

        beachArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        habitatArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_beach2.setAdapter(beachArrayAdapter);
        spinner_habitat2.setAdapter(habitatArrayAdapter);
    }

    public void confirm(){
        try {
            connection = dataOpenHelper.getWritableDatabase();

            String observations = txt_observationField2.getText().toString();
            String date = dateLocalization2.getText().toString();
            Float gpsEast = Float.valueOf(txt_gpsEast2.getText().toString());
            Float gpsSouth = Float.valueOf(txt_gpsSouth2.getText().toString());
            beach = (Beach) spinner_beach2.getSelectedItem();
            habitat = (Habitat) spinner_habitat2.getSelectedItem();

            toActNest = habitat.getIdhabitat()+
                    "!"+beach.getBeach()+
                    "!"+gpsSouth+
                    "!"+gpsEast+
                    "!"+date+
                    "!"+observations;

            toNestWTurtleData = habitat.getIdhabitat()+
                    "="+beach.getBeach()+
                    "="+gpsSouth+
                    "="+gpsEast+
                    "="+date;

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

        String observations = txt_observationField2.getText().toString();
        String distance = txt_gpsEast2.getText().toString();
        String height = txt_gpsSouth2.getText().toString();
        String date = dateLocalization2.getText().toString();

        if(res = isEmptyField(date)){
            dateLocalization2.requestFocus();
        }else
            if(res = isEmptyField(height)){
                txt_gpsSouth2.requestFocus();
            }else
                if(res = isEmptyField(distance)){
                    txt_gpsEast2.requestFocus();
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

                    Intent it = new Intent(this, ActNest2.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("toActNest2", toActNest +"#"+toNestWTurtleData);
                    it.putExtras(bundle);

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
