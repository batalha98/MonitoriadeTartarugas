package com.example.monitoriadetartarugas.addData;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoriadetartarugas.ListingData;
import com.example.monitoriadetartarugas.R;
import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.WindCategoryController;
import com.example.monitoriadetartarugas.domain.controller.WindDirectionController;
import com.example.monitoriadetartarugas.domain.entitys.WindCategory;
import com.example.monitoriadetartarugas.domain.entitys.WindDirection;

public class AddWD extends AppCompatActivity {
    private EditText txt_wd;
    private Button btn_addWD;
    private WindDirectionController windDirectionController;
    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;
    private String wdToModify;
    private int pilot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_w_d);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pilot = 0;
        txt_wd = findViewById(R.id.txt_wd);
        btn_addWD = findViewById(R.id.btn_addWD);
        dataOpenHelper = new DataOpenHelper(this);
        verifyParameters();

        btn_addWD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()==false){
                    confirm();
                }
            }
        });
    }

    private void confirm(){
        String str_wd = txt_wd.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("idBtn","wd");
        connection = dataOpenHelper.getWritableDatabase();

        if(pilot==1){
            windDirectionController = new WindDirectionController(connection);
            WindDirection windDirection = windDirectionController.fetchOne(wdToModify);

            windDirectionController.edit(str_wd, windDirection);

            Intent intent = new Intent(getApplicationContext(), ListingData.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "Wind direction editted successfully!", Toast.LENGTH_LONG).show();
        }else{
            windDirectionController = new WindDirectionController(connection);

            if(windDirectionController.fetchOne(str_wd)!=null){
                Toast.makeText(getApplicationContext(), "Wind direction already exists!", Toast.LENGTH_SHORT).show();
            }else{
                WindDirection windDirection = new WindDirection();
                windDirection.setName(str_wd);

                windDirectionController.insert(windDirection);

                Intent intent = new Intent(getApplicationContext(), ListingData.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                Toast.makeText(getApplicationContext(), "Wind direction added succesfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyParameters(){
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("wd")){
            wdToModify = bundle.getString("wd");

            txt_wd.setText(wdToModify);

            pilot = 1;
            btn_addWD.setText("Edit");
        }
    }

    private boolean validateFields(){
        boolean res = false;

        String wd = txt_wd.getText().toString();

        if(res = isEmptyField(wd)){
            txt_wd.requestFocus();
        }

        if(res){
            Toast.makeText(getApplicationContext(), "Fill the blank field!", Toast.LENGTH_SHORT).show();
        }

        return res;
    }

    private boolean isEmptyField(String value){
        boolean result = (TextUtils.isEmpty(value) || value.trim().isEmpty());

        return result;
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}