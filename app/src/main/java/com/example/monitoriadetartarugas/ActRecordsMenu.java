package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.util.Utils;
import com.example.monitoriadetartarugas.verifyPermission.VerifyPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActRecordsMenu extends AppCompatActivity{
    Button button_turtleAndNest;
    Button button_nestWithoutTurtle;
    Button button_exportData;
    SQLiteToExcel sqLiteToExcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_records_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button_turtleAndNest = findViewById(R.id.button_turtleAndNestRecords);
        button_nestWithoutTurtle = findViewById(R.id.button_nestWithoutTurtleRecords);
        button_exportData = findViewById(R.id.btn_exportData);

        button_turtleAndNest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listRecords(v, "turtleNest");
            }
        });

        button_nestWithoutTurtle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listRecords(v, "nestWithoutTurtle");
            }
        });

        VerifyPermission.verifyStoragePermissions(this);

        button_exportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String directory_path = Environment.getExternalStorageDirectory().getPath()+ "/TurtlesDataExported/";
                File file = new File(directory_path);
                List<String> tables = new ArrayList<>();

                if(!file.exists()){
                    file.mkdirs();
                }

                sqLiteToExcel = new SQLiteToExcel(getApplicationContext(),
                        DataOpenHelper.DB_NAME, directory_path);


                sqLiteToExcel.exportSingleTable("turtlenestdata","turtles.xls", new SQLiteToExcel.ExportListener(){
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Utils.showSnackBar(v, "Exportado com sucesso!");
                    }

                    @Override
                    public void onError(Exception e) {
                        Utils.showSnackBar(v, e.getMessage());
                    }
                });
            }
        });
    }

    private void listRecords(View view, String id){
        Intent it = new Intent(this, ActListingRecords.class);

        Bundle bundle = new Bundle();
        bundle.putString("idBtn",id);
        it.putExtras(bundle);

        startActivityForResult(it, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                Intent intent = new Intent(this, Act_Main.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}