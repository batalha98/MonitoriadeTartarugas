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
import com.example.monitoriadetartarugas.domain.entitys.WindCategory;

public class AddWC extends AppCompatActivity {
    private EditText txt_wc;
    private Button btn_addWC;
    private WindCategoryController windCategoryController;
    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;
    private String wcToModify;
    private int pilot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_w_c);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txt_wc = findViewById(R.id.txt_wc);
        btn_addWC = findViewById(R.id.btn_addWC);
        dataOpenHelper = new DataOpenHelper(this);
        verifyParameters();

        btn_addWC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()==false){
                    confirm();
                }
            }
        });
    }

    private void confirm(){
        String str_wc = txt_wc.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("idBtn","wc");
        connection = dataOpenHelper.getWritableDatabase();

        if(pilot==1){
            windCategoryController = new WindCategoryController(connection);
            WindCategory windCategory = windCategoryController.fetchOne(wcToModify);

            windCategoryController.edit(str_wc, windCategory);

            Intent intent = new Intent(getApplicationContext(), ListingData.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "Wind category editted successfully!", Toast.LENGTH_LONG).show();
        }else{
            windCategoryController = new WindCategoryController(connection);

            if(windCategoryController.fetchOne(str_wc)!=null){
                Toast.makeText(getApplicationContext(), "Wind category already exists!", Toast.LENGTH_SHORT).show();
            }else{
                WindCategory windCategory = new WindCategory();
                windCategory.setName(str_wc);

                windCategoryController.insert(windCategory);

                Intent intent = new Intent(getApplicationContext(), ListingData.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                Toast.makeText(getApplicationContext(), "Wind category added succesfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyParameters(){
        Bundle bundle = getIntent().getExtras();

        if(bundle!=null && bundle.containsKey("wc")){
            wcToModify = bundle.getString("wc");

            txt_wc.setText(wcToModify);

            pilot = 1;
            btn_addWC.setText("Edit");
        }
    }

    private boolean validateFields(){
        boolean res = false;

        String wc = txt_wc.getText().toString();

        if(res = isEmptyField(wc)){
            txt_wc.requestFocus();
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