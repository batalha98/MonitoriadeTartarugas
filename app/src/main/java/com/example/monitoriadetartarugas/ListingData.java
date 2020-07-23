package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.monitoriadetartarugas.addData.AddActivities;
import com.example.monitoriadetartarugas.addData.AddBeach;
import com.example.monitoriadetartarugas.addData.AddHabitat;
import com.example.monitoriadetartarugas.addData.AddIsland;
import com.example.monitoriadetartarugas.addData.AddSpecie;
import com.example.monitoriadetartarugas.addData.AddUser;
import com.example.monitoriadetartarugas.addData.AddWC;
import com.example.monitoriadetartarugas.addData.AddWD;
import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.ActivitiesController;
import com.example.monitoriadetartarugas.domain.controller.BeachController;
import com.example.monitoriadetartarugas.domain.controller.HabitatController;
import com.example.monitoriadetartarugas.domain.controller.IslandController;
import com.example.monitoriadetartarugas.domain.controller.SpecieController;
import com.example.monitoriadetartarugas.domain.controller.UsersController;
import com.example.monitoriadetartarugas.domain.controller.WindCategoryController;
import com.example.monitoriadetartarugas.domain.controller.WindDirectionController;
import com.example.monitoriadetartarugas.domain.entitys.Activities;
import com.example.monitoriadetartarugas.domain.entitys.Beach;
import com.example.monitoriadetartarugas.domain.entitys.Habitat;
import com.example.monitoriadetartarugas.domain.entitys.Island;
import com.example.monitoriadetartarugas.domain.entitys.Specie;
import com.example.monitoriadetartarugas.domain.entitys.Users;
import com.example.monitoriadetartarugas.domain.entitys.WindCategory;
import com.example.monitoriadetartarugas.domain.entitys.WindDirection;
import com.example.monitoriadetartarugas.listViewAdapter.ActivitiesAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.BeachAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.HabitatAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.IslandAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.SpeciesAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.UsersAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.WcAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.WdAdapter;

public class ListingData extends AppCompatActivity {
    private ListView listView;
    private String idData, idToModal;
    private Bundle bundle;
    private FloatingActionButton fab;

    private HabitatAdapter habitatAdapter;
    private SpeciesAdapter speciesAdapter;
    private WdAdapter wdAdapter;
    private WcAdapter wcAdapter;
    private IslandAdapter islandAdapter;
    private UsersAdapter usersAdapter;
    private BeachAdapter beachAdapter;
    private WindDirectionController wdController;
    private WindCategoryController wcController;
    private ActivitiesAdapter activitiesAdapter;
    private IslandController islandController;
    private ActivitiesController activitiesController;
    private UsersController usersController;
    private BeachController beachController;
    private SpecieController specieController;
    private HabitatController habitatController;

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

        habitatController = new HabitatController(connection);
        specieController = new SpecieController(connection);
        wdController = new WindDirectionController(connection);
        wcController = new WindCategoryController(connection);
        islandController = new IslandController(connection);
        usersController = new UsersController(connection);
        activitiesController = new ActivitiesController(connection);
        beachController = new BeachController(connection);

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
                activitiesAdapter = new ActivitiesAdapter(activitiesController.fetchAll(),this);
                listView.setAdapter(activitiesAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddActivities.class);

                        startActivity(intent);
                    }
                });
            }else if(idData.equals("beach")){
                beachAdapter = new BeachAdapter(beachController.fetchAll(),this);
                listView.setAdapter(beachAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddBeach.class);

                        startActivity(intent);
                    }
                });
            }else if(idData.equals("island")){
                islandAdapter = new IslandAdapter(islandController.fetchAll(),this);
                listView.setAdapter(islandAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddIsland.class);

                        startActivity(intent);
                    }
                });
            }else if(idData.equals("wc")){
                wcAdapter = new WcAdapter(wcController.fetchAll(),this);
                listView.setAdapter(wcAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddWC.class);

                        startActivity(intent);
                    }
                });
            }else if(idData.equals("wd")){
                wdAdapter = new WdAdapter(wdController.fetchAll(),this);
                listView.setAdapter(wdAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddWD.class);

                        startActivity(intent);
                    }
                });
            }else if(idData.equals("specie")){
                speciesAdapter = new SpeciesAdapter(specieController.fetchAll(),this);
                listView.setAdapter(speciesAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddSpecie.class);

                        startActivity(intent);
                    }
                });
            }else if(idData.equals("habitat")){
                habitatAdapter = new HabitatAdapter(habitatController.fetchAll(),this);
                listView.setAdapter(habitatAdapter);

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), AddHabitat.class);

                        startActivity(intent);
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
                }else if(idData.equals("activities")){
                    Activities activities = (Activities) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "activities"+"!"+activities.getActivity();
                }else if(idData.equals("beach")){
                    Beach beach = (Beach) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "beach"+"!"+beach.getBeach();
                }else if(idData.equals("island")){
                    Island island = (Island) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "island"+"!"+island.getIsland();
                }else if(idData.equals("wc")){
                    WindCategory wc = (WindCategory) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "wc"+"!"+wc.getName();
                }else if(idData.equals("wd")){
                    WindDirection wd = (WindDirection) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "wd"+"!"+wd.getName();
                }else if(idData.equals("specie")){
                    Specie specie = (Specie) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "specie"+"!"+specie.getSpecie();
                }else if(idData.equals("habitat")){
                    Habitat habitat = (Habitat) parent.getItemAtPosition(position);
                    modalBottomSheet = new ModalBottomSheet();
                    idToModal = "habitat"+"!"+habitat.getHabitat();
                }

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