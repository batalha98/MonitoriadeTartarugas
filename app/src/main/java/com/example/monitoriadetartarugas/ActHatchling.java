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
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.HatchlingController;
import com.example.monitoriadetartarugas.domain.controller.NestController;
import com.example.monitoriadetartarugas.domain.entitys.Hatchling;
import com.example.monitoriadetartarugas.domain.entitys.Nest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActHatchling extends AppCompatActivity {
    private EditText dateOfBirthField;
    private EditText txt_nrHatched;
    private EditText txt_nrDiedInNest;
    private EditText txt_nrAliveInNest;
    private EditText txt_nrUndeveloped;
    private EditText txt_nrUnhatched;
    private EditText txt_nrPredatedEggs;
    private EditText txt_HatchDescription;

    private DatePickerDialog picker;
    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    private String[] strings;
    private String receivedFromNest, toTurtleNestData;
    private Hatchling hatchling;
    private NestController nestController;
    private HatchlingController hatchlingController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_hatchling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateOfBirthField = findViewById(R.id.dateOfBirthField);
        dateOfBirthField.setInputType(InputType.TYPE_NULL);
        dateOfBirthField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(calendar.DAY_OF_MONTH);
                int month = calendar.get(calendar.MONTH);
                int year = calendar.get(calendar.YEAR);

                picker = new DatePickerDialog(ActHatchling.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateOfBirthField.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

                picker.show();
            }
        });

        txt_nrHatched = findViewById(R.id.txt_nrHatched);
        txt_nrDiedInNest = findViewById(R.id.txt_nrDiedInNest);
        txt_nrAliveInNest = findViewById(R.id.txt_nrAliveInNest);
        txt_nrUndeveloped = findViewById(R.id.txt_nrUndeveloped);
        txt_nrUnhatched = findViewById(R.id.txt_nrUnhatched);
        txt_nrPredatedEggs = findViewById(R.id.txt_nrPredatedEggs);
        txt_HatchDescription = findViewById(R.id.txt_HatchDescription);

        dataOpenHelper = new DataOpenHelper(this);
    }

    public void confirm() throws ParseException {

            connection = dataOpenHelper.getWritableDatabase();
            hatchlingController = new HatchlingController(connection);

            connection = dataOpenHelper.getReadableDatabase();
            nestController = new NestController(connection);

            hatchling = new Hatchling();
            Nest nest = new Nest();
            Bundle bundle = getIntent().getExtras();
            String date = dateOfBirthField.getText().toString();
            String nrHatched = txt_nrHatched.getText().toString();
            String diedInNest = txt_nrDiedInNest.getText().toString();
            String aliveInNest = txt_nrAliveInNest.getText().toString();
            String undeveloped = txt_nrUndeveloped.getText().toString();
            String unhatched = txt_nrUnhatched.getText().toString();
            String predatedEggs = txt_nrPredatedEggs.getText().toString();
            String description = txt_HatchDescription.getText().toString();

            if(bundle != null){
                receivedFromNest = bundle.getString("observationFromActNest");

                strings = receivedFromNest.split("#");

                nest = nestController.fetchOne(Integer.parseInt(strings[1]));
            }

            hatchling.setIdnest(nest);
            hatchling.setHatched(Integer.parseInt(nrHatched));
            hatchling.setDataa(new Date(date));
            hatchling.setDied_in_nest(Integer.parseInt(diedInNest));
            hatchling.setAlive_in_nest(Integer.parseInt(aliveInNest));
            hatchling.setUndeveloped(Integer.parseInt(undeveloped));
            hatchling.setPredated_eggs(Integer.parseInt(predatedEggs));
            hatchling.setUnhatched(Integer.parseInt(unhatched));
            hatchling.setDescription(description);

            hatchlingController.insert(hatchling);

            toTurtleNestData = strings[2]+"!"
                    +Integer.parseInt(nrHatched)+"-"
                    +date+"-"
                    +Integer.parseInt(diedInNest)+"-"
                    +Integer.parseInt(aliveInNest)+"-"
                    +Integer.parseInt(undeveloped)+"-"
                    +Integer.parseInt(predatedEggs)+"-"
                    +Integer.parseInt(unhatched);
    }

    public boolean validateFields(){
        boolean res = false;

        String date = dateOfBirthField.getText().toString();
        String nrHatched = txt_nrHatched.getText().toString();
        String diedInNest = txt_nrDiedInNest.getText().toString();
        String aliveInNest = txt_nrAliveInNest.getText().toString();
        String undeveloped = txt_nrUndeveloped.getText().toString();
        String unhatched = txt_nrUnhatched.getText().toString();
        String predatedEggs = txt_nrPredatedEggs.getText().toString();

        if(res = isEmptyField(date)){
            dateOfBirthField.requestFocus();
        }else
            if(res = isEmptyField(nrHatched)){
                txt_nrHatched.requestFocus();
            }else
                if(res = isEmptyField(diedInNest)){
                    txt_nrDiedInNest.requestFocus();
                }else
                    if(res = isEmptyField(aliveInNest)){
                        txt_nrAliveInNest.requestFocus();
                    }else
                        if(res = isEmptyField(undeveloped)){
                            txt_nrUndeveloped.requestFocus();
                        }else
                            if(res = isEmptyField(unhatched)){
                                txt_nrUnhatched.requestFocus();
                            }else
                                if(res = isEmptyField(predatedEggs)){
                                    txt_nrPredatedEggs.requestFocus();
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
                    try {
                        confirm();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Intent it = new Intent(this, ActTaggs.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("observation", Integer.parseInt(strings[1])+"_"+strings[0]+"_"+toTurtleNestData);
                    it.putExtras(bundle);

                    startActivityForResult(it, 0);
                }
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
