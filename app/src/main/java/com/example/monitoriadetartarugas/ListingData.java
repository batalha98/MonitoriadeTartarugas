package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.UsersController;
import com.example.monitoriadetartarugas.listViewAdapter.UsersAdapter;

public class ListingData extends AppCompatActivity {
    private ListView listView;
    private String idData;
    private Bundle bundle;
    private FloatingActionButton fab;
    private UsersAdapter usersAdapter;
    private UsersController usersController;
    private SQLiteDatabase connection;
    private DataOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_data);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        openHelper = new DataOpenHelper(this);
        connection = openHelper.getReadableDatabase();

        usersController = new UsersController(connection);

        bundle = getIntent().getExtras();
        fab = findViewById(R.id.fabAddNewData);

        if(bundle!=null){
            idData = bundle.getString("idBtn");

            if(idData.equals("users")){
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddUser.class);

                        startActivity(intent);
                    }
                });
            }else if(idData.equals("activities")){
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }else if(idData.equals("beach")){
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }else if(idData.equals("island")){
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }else if(idData.equals("wc")){
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }else if(idData.equals("wd")){
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }

        listView = findViewById(R.id.listview_data);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        usersAdapter = new UsersAdapter(usersController.fetchAll(),this);
        listView.setAdapter(usersAdapter);
    }

    @Override
    public void onBackPressed(){
        finish();
        Intent it = new Intent(this, AdminDashboard.class);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.list_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

//    AbsListView.OnLongClickListener modeListener = new AbsListView.OnLongClickListener(){
//        @Override
//        public boolean onLongClick(View v) {
//
//
//            return false;
//        }
//    };
}