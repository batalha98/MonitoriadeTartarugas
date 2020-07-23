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
import com.example.monitoriadetartarugas.domain.controller.ActivitiesController;
import com.example.monitoriadetartarugas.domain.entitys.Activities;

public class AddActivities extends AppCompatActivity {
    private Button btn_addActivity;
    private ActivitiesController activitiesController;
    private DataOpenHelper dataOpenHelper;
    private SQLiteDatabase connection;
    private EditText txt_activity;
    private int pilot;
    private String activityToMofify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activities);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pilot=0;
        txt_activity = findViewById(R.id.txt_activityName);
        btn_addActivity = findViewById(R.id.btn_addActivity);

        dataOpenHelper = new DataOpenHelper(this);
        verifyParameters();

        btn_addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()==false){
                    confirm();
                }
            }
        });
    }

    private void confirm(){
        String activity = txt_activity.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString("idBtn","activities");

        if(pilot==1){
            connection = dataOpenHelper.getWritableDatabase();
            activitiesController = new ActivitiesController(connection);

            activitiesController.edit(activityToMofify,activity);

            Intent intent = new Intent(getApplicationContext(), ListingData.class);
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
            Toast.makeText(getApplicationContext(), "Activity editted successfully!", Toast.LENGTH_LONG).show();
        }else{
            connection = dataOpenHelper.getWritableDatabase();
            activitiesController = new ActivitiesController(connection);

            if(activitiesController.fetchOne(activity)!=null){
                Toast.makeText(getApplicationContext(), "Activity name already exists!", Toast.LENGTH_SHORT).show();
            }else{
                Activities activities = new Activities();
                activities.setActivity(activity);

                activitiesController.insert(activities);

                Intent intent = new Intent(getApplicationContext(), ListingData.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                Toast.makeText(getApplicationContext(), "Activity added succesfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void verifyParameters(){
        Bundle bundle = getIntent().getExtras();

        connection = dataOpenHelper.getReadableDatabase();
        activitiesController = new ActivitiesController(connection);

        if(bundle!=null && bundle.containsKey("activities")){
            activityToMofify = bundle.getString("activities");

            txt_activity.setText(activityToMofify);

            pilot = 1;
            btn_addActivity.setText("Edit");
        }
    }

    private boolean validateFields(){
        boolean res = false;

        String activity = txt_activity.getText().toString();

        if(res = isEmptyField(activity)){
            txt_activity.requestFocus();
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