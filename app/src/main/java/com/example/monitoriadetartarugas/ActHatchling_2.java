package com.example.monitoriadetartarugas;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.HatchlingController;
import com.example.monitoriadetartarugas.domain.controller.NestController;
import com.example.monitoriadetartarugas.domain.controller.NestWithoutTurtleController;
import com.example.monitoriadetartarugas.domain.controller.SpecieController;
import com.example.monitoriadetartarugas.domain.entitys.Hatchling;
import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.NestWithoutTurtle;
import com.example.monitoriadetartarugas.domain.entitys.Specie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActHatchling_2 extends AppCompatActivity {
    private EditText dateOfBirthField2;
    private EditText txt_nrHatched2;
    private EditText txt_nrDiedInNest2;
    private EditText txt_nrAliveInNest2;
    private EditText txt_nrUndeveloped2;
    private EditText txt_nrUnhatched2;
    private EditText txt_nrPredatedEggs2;
    private EditText txt_HatchDescription2;

    private DatePickerDialog picker;
    private Hatchling hatchling;
    private NestWithoutTurtle nestWithoutTurtle;

    private NestWithoutTurtleController nestWithoutTurtleController;
    private SpecieController specieController;
    private NestController nestController;
    private HatchlingController hatchlingController;

    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_hatchling_2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateOfBirthField2 = findViewById(R.id.dateOfBirthField2);
        dateOfBirthField2.setInputType(InputType.TYPE_NULL);
        dateOfBirthField2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(calendar.DAY_OF_MONTH);
                int month = calendar.get(calendar.MONTH);
                int year = calendar.get(calendar.YEAR);

                picker = new DatePickerDialog(ActHatchling_2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateOfBirthField2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

                picker.show();
            }
        });

        txt_nrHatched2 = findViewById(R.id.txt_nrHatched2);
        txt_nrDiedInNest2 = findViewById(R.id.txt_nrDiedInNest2);
        txt_nrAliveInNest2 = findViewById(R.id.txt_nrAliveInNest2);
        txt_nrUndeveloped2 = findViewById(R.id.txt_nrUndeveloped2);
        txt_nrUnhatched2 = findViewById(R.id.txt_nrUnhatched2);
        txt_nrPredatedEggs2 = findViewById(R.id.txt_nrPredatedEggs2);
        txt_HatchDescription2 = findViewById(R.id.txt_HatchDescription2);

        dataOpenHelper = new DataOpenHelper(this);
    }

    public void confirm() throws ParseException {
        connection = dataOpenHelper.getWritableDatabase();
        hatchlingController = new HatchlingController(connection);
        nestWithoutTurtleController = new NestWithoutTurtleController(connection);
        nestController = new NestController(connection);
        specieController = new SpecieController(connection);

        nestWithoutTurtle = new NestWithoutTurtle();
        hatchling = new Hatchling();

        Nest nest = new Nest();
        Specie specie = new Specie();
        Bundle bundle = getIntent().getExtras();

        String date = dateOfBirthField2.getText().toString();
        String nrHatched = txt_nrHatched2.getText().toString();
        String diedInNest = txt_nrDiedInNest2.getText().toString();
        String aliveInNest = txt_nrAliveInNest2.getText().toString();
        String undeveloped = txt_nrUndeveloped2.getText().toString();
        String unhatched = txt_nrUnhatched2.getText().toString();
        String predatedEggs = txt_nrPredatedEggs2.getText().toString();
        String description = txt_HatchDescription2.getText().toString();

        if (bundle != null) {
            String receivedFromNest = bundle.getString("idnestAndSpecie2");

            String[] strings = receivedFromNest.split("-");

            nest = nestController.fetchOne(Integer.parseInt(strings[0]));
            specie = specieController.fetchOne(Integer.parseInt(strings[1]));

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("por guardar na db: ");
            alertDialog.setMessage(strings[0] +", specie: "+strings[1]);
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }

        /*hatchling.setIdnest(nest);
        hatchling.setHatched(Integer.parseInt(nrHatched));
        hatchling.setDataa(new Date(date));
        hatchling.setDied_in_nest(Integer.parseInt(diedInNest));
        hatchling.setAlive_in_nest(Integer.parseInt(aliveInNest));
        hatchling.setUndeveloped(Integer.parseInt(undeveloped));
        hatchling.setPredated_eggs(Integer.parseInt(predatedEggs));
        hatchling.setUnhatched(Integer.parseInt(unhatched));
        hatchling.setDescription(description);

        nestWithoutTurtle.setIdnest(nest);
        nestWithoutTurtle.setIdspecie(specie);

        hatchlingController.insert(hatchling);

        nestWithoutTurtleController.insert(nestWithoutTurtle);*/
    }

    public boolean validateFields(){
        boolean res = false;

        String date = dateOfBirthField2.getText().toString();
        String nrHatched = txt_nrHatched2.getText().toString();
        String diedInNest = txt_nrDiedInNest2.getText().toString();
        String aliveInNest = txt_nrAliveInNest2.getText().toString();
        String undeveloped = txt_nrUndeveloped2.getText().toString();
        String unhatched = txt_nrUnhatched2.getText().toString();
        String predatedEggs = txt_nrPredatedEggs2.getText().toString();
        String description = txt_HatchDescription2.getText().toString();

        if(res = isEmptyField(date)){
            dateOfBirthField2.requestFocus();
        }else
            if(res = isEmptyField(nrHatched)){
                txt_nrHatched2.requestFocus();
            }else
                if(res = isEmptyField(diedInNest)){
                    txt_nrDiedInNest2.requestFocus();
                }else
                    if(res = isEmptyField(aliveInNest)){
                        txt_nrAliveInNest2.requestFocus();
                    }else
                        if(res = isEmptyField(undeveloped)){
                            txt_nrUndeveloped2.requestFocus();
                        }else
                            if(res = isEmptyField(unhatched)){
                                txt_nrUnhatched2.requestFocus();
                            }else
                                if(res = isEmptyField(predatedEggs)){
                                    txt_nrPredatedEggs2.requestFocus();
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

                   /* Toast.makeText(this, "Info added successfully!", Toast.LENGTH_LONG).show();
                    Intent it = new Intent(this, ActListingRecords.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("idBtn","nestWithoutTurtle");
                    it.putExtras(bundle);

                    startActivityForResult(it,  0);*/
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
