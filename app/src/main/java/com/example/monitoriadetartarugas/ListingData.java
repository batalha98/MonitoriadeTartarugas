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
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.UsersController;
import com.example.monitoriadetartarugas.domain.entitys.Users;
import com.example.monitoriadetartarugas.listViewAdapter.UsersAdapter;

public class ListingData extends AppCompatActivity {
    private ListView listView;
    private String idData, idToModal;
    private Bundle bundle;
    private FloatingActionButton fab;
    private UsersAdapter usersAdapter;
    private UsersController usersController;
    private SQLiteDatabase connection;
    private DataOpenHelper openHelper;
    private ModalBottomSheet modalBottomSheet;

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
        listView = findViewById(R.id.listview_data);

        if(bundle!=null){
            idData = bundle.getString("idBtn");

            if(idData.equals("users")){
                usersAdapter = new UsersAdapter(usersController.fetchAll(),this);
                listView.setAdapter(usersAdapter);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                if(idData.equals("users")){
                    Users user = (Users) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "users"+"!"+user.getEmail();
                }/*else if(idData.equals("beach")){
                    Users user = (Users) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "users"+"!"+user.getEmail();
                }else if(idData.equals("activities")){
                    Users user = (Users) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "users"+"!"+user.getEmail();
                }else if(idData.equals("island")){
                    Users user = (Users) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "users"+"!"+user.getEmail();
                }else if(idData.equals("wc")){
                    Users user = (Users) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "users"+"!"+user.getEmail();
                }else if(idData.equals("wd")){
                    Users user = (Users) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "users"+"!"+user.getEmail();
                }*/

                bundle.putString("ToModify", idToModal);
                modalBottomSheet.setArguments(bundle);

                modalBottomSheet.show(getSupportFragmentManager(), "modalMenu");
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        Intent it = new Intent(this, AdminDashboard.class);
        startActivity(it);
    }
}