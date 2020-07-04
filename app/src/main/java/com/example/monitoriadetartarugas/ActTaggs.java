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
import android.widget.Toast;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.NestController;
import com.example.monitoriadetartarugas.domain.controller.TurtleController;
import com.example.monitoriadetartarugas.domain.controller.TurtleNestController;
import com.example.monitoriadetartarugas.domain.controller.TurtleTaggsController;
import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.Turtle;
import com.example.monitoriadetartarugas.domain.entitys.TurtleNest;
import com.example.monitoriadetartarugas.domain.entitys.TurtleTaggs;

import java.util.Calendar;
import java.util.Date;

public class ActTaggs extends AppCompatActivity {
    private EditText tagginDate;
    private EditText txt_nrLeftRing;
    private EditText txt_nrRightRing;
    private EditText txt_nrInternalTag;
    private EditText txt_cclMeasure;
    private EditText txt_cwlMeasure;

    private DatePickerDialog picker;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    private String[] strings;
    private String receivedFromHatchling, toTurtle = "";
    private TurtleNest turtleNest;
    private TurtleTaggs turtleTaggs;
    private TurtleController turtleController;
    private TurtleNestController turtleNestController;
    private NestController nestController;

    private TurtleTaggsController turtleTaggsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_taggs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tagginDate = findViewById(R.id.tagginDate);
        tagginDate.setInputType(InputType.TYPE_NULL);
        tagginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(calendar.DAY_OF_MONTH);
                int month = calendar.get(calendar.MONTH);
                int year = calendar.get(calendar.YEAR);

                picker = new DatePickerDialog(ActTaggs.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tagginDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

                picker.show();
            }
        });

        txt_nrLeftRing = findViewById(R.id.txt_nrLeftRing);
        txt_nrRightRing = findViewById(R.id.txt_nrRightRing);
        txt_nrInternalTag = findViewById(R.id.txt_nrInternalTag);
        txt_cclMeasure = findViewById(R.id.txt_cclMeasure);
        txt_cwlMeasure = findViewById(R.id.txt_cwlMeasure);

        dataOpenHelper = new DataOpenHelper(this);
    }

    public void confirm(){
        try {
            Bundle bundle = getIntent().getExtras();

            Date tagDate = new Date(tagginDate.getText().toString());
            int leftRing = Integer.parseInt(txt_nrLeftRing.getText().toString());
            int rightRing = Integer.parseInt(txt_nrRightRing.getText().toString());
            int internalTag = Integer.parseInt(txt_nrInternalTag.getText().toString());
            Double cclMeasure = Double.valueOf(txt_cclMeasure.getText().toString());
            Double cwlMeasure = Double.valueOf(txt_cwlMeasure.getText().toString());

            if(bundle != null){
                receivedFromHatchling = bundle.getString("observation");
                //strings[0]: idnest
                //strings[1]: observation
            }

            toTurtle = receivedFromHatchling+"@"+tagDate+
                    "-"+leftRing+
                    "-"+rightRing+
                    "-"+internalTag+
                    "-"+cclMeasure+
                    "-"+cwlMeasure;

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

        String tagDate = tagginDate.getText().toString();
        String leftRing = txt_nrLeftRing.getText().toString();
        String rightRing = txt_nrRightRing.getText().toString();
        String internalTag = txt_nrInternalTag.getText().toString();
        String cclMeasure = txt_cclMeasure.getText().toString();
        String cwlMeasure = txt_cwlMeasure.getText().toString();

        if(res = isEmptyField(tagDate)){
            tagginDate.requestFocus();
        }else
            if(res = isEmptyField(leftRing)){
                txt_nrLeftRing.requestFocus();
            }else
                if(res = isEmptyField(rightRing)){
                    txt_nrRightRing.requestFocus();
                }else
                    if(res = isEmptyField(internalTag)){
                        txt_nrInternalTag.requestFocus();
                    }else
                        if(res = isEmptyField(cclMeasure)){
                            txt_cclMeasure.requestFocus();
                        }else
                            if(res = isEmptyField(cwlMeasure)){
                                txt_cwlMeasure.requestFocus();
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
                if(validateFields() == false) {
                    confirm();

                    Intent it = new Intent(this, ActTurtle.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("finalStage", toTurtle);
                    it.putExtras(bundle);

                    startActivityForResult(it, 0);
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}