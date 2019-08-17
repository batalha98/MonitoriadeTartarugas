package com.example.monitoriadetartarugas;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.NestWithoutTurtleController;
import com.example.monitoriadetartarugas.domain.controller.TurtleNestController;
import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.NestWithoutTurtle;
import com.example.monitoriadetartarugas.domain.entitys.Turtle;
import com.example.monitoriadetartarugas.domain.entitys.TurtleNest;
import com.example.monitoriadetartarugas.listViewAdapter.NestWithoutTurtleAdapter;
import com.example.monitoriadetartarugas.listViewAdapter.TurtleNestAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActListingRecords extends AppCompatActivity {
    private ListView listView;
    private String idDataName;
    private String[] strings;
    private Bundle bundle;

    private FloatingActionButton fab;
    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    private TurtleNestController turtleNestController;
    private NestWithoutTurtleController nestWithoutTurtleController;

    private TurtleNestAdapter turtleNestAdapter;
    private NestWithoutTurtleAdapter nestWithoutTurtleAdapter;

    private List<String> userSelection1;
    private List<TurtleNest> turtleNests;
    private List<String> userSelection2;
    private List<NestWithoutTurtle> nestWithoutTurtles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_listing_records);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bundle = getIntent().getExtras();
        fab = findViewById(R.id.fabAddItems);

        if(bundle != null) {
            idDataName = bundle.getString("idBtn");

            if(idDataName.equals("turtleNest")) {
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ActTurtle.class);
                        startActivity(intent);
                    }
                });
            }else{
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ActNest2.class);
                        startActivity(intent);
                    }
                });
            }
        }

        createConnection();

        userSelection1 = new ArrayList<>();
        turtleNests = new ArrayList<>();
        userSelection2 = new ArrayList<>();
        nestWithoutTurtles = new ArrayList<>();

        for(int i=0; i<nestWithoutTurtleController.fetchAll().size() ;i++) nestWithoutTurtles.add(null);

        for(int i=0; i<turtleNestController.fetchAll().size() ;i++) turtleNests.add(null);

        listView = findViewById(R.id.listViewTurtles);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(modeListener);

        if(bundle != null) {
            if(idDataName.equals("turtleNest")) {
                turtleNestAdapter = new TurtleNestAdapter(turtleNestController.fetchAll(), this);
                listView.setAdapter(turtleNestAdapter);
            }else{
                nestWithoutTurtleAdapter =
                        new NestWithoutTurtleAdapter(nestWithoutTurtleController.fetchAll(), this);
                listView.setAdapter(nestWithoutTurtleAdapter);
            }
        }
    }

    public void createConnection(){
        try {
            dataOpenHelper = new DataOpenHelper(this);

            connection = dataOpenHelper.getWritableDatabase();

            turtleNestController = new TurtleNestController(connection);
            nestWithoutTurtleController = new NestWithoutTurtleController(connection);
        }catch(SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_main_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(idDataName.equals("turtleNest")) {
            turtleNestAdapter = new TurtleNestAdapter(turtleNestController.fetchAll(), this);
            listView.setAdapter(turtleNestAdapter);
        }else
        if(idDataName.equals("nestWithoutTurtle")){
            nestWithoutTurtleAdapter =
                    new NestWithoutTurtleAdapter(nestWithoutTurtleController.fetchAll(), this);
            listView.setAdapter(nestWithoutTurtleAdapter);
        }
    }

    AbsListView.MultiChoiceModeListener modeListener= new AbsListView.MultiChoiceModeListener(){

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(idDataName.equals("turtleNest")) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        turtleNestAdapter.removeItems(turtleNests);

                        //remove also in turtlesDB
                        for (TurtleNest turtleNest : turtleNests) {
                            if(turtleNest!=null) {
                                turtleNestController.remove(turtleNest.getIdnest().getIdnest());
                            }
                        }

                        mode.finish();
                        Intent intent = getIntent();
                        startActivity(intent);
                        return true;
                }
            }else{
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        nestWithoutTurtleAdapter.removeItems(nestWithoutTurtles);

                        //remove also in turtlesDB
                        for (NestWithoutTurtle withoutTurtle : nestWithoutTurtles) {
                            if(withoutTurtle!=null) {
                                nestWithoutTurtleController.remove(withoutTurtle.getIdnest().getIdnest());
                            }
                        }

                        mode.finish();
                        Intent intent = getIntent();
                        startActivity(intent);

                        return true;
                }
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            if(idDataName.equals("turtleNest")){
                String turtleNest = turtleNestController.fetchAll().get(position).toString();
                TurtleNest tn = turtleNestController.fetchAll().get(position);
                //strings = turtleNest.split("-");

                if (userSelection1.contains(turtleNest)) {
                    userSelection1.remove(turtleNest);
                    turtleNests.remove(position);
                } else {
                    userSelection1.add(turtleNest);
                    turtleNests.add(position, tn);
                }

                mode.setTitle(userSelection1.size() + " items selected...");
            }else{
                String nestWTurtle = nestWithoutTurtleController.fetchAll().get(position).toString();
                NestWithoutTurtle withoutTurtle= nestWithoutTurtleController.fetchAll().get(position);
                //strings = nestWTurtle.split("-");

                if (userSelection2.contains(nestWTurtle)) {
                    userSelection2.remove(nestWTurtle);
                    nestWithoutTurtles.remove(position);

                } else {
                    userSelection2.add(nestWTurtle);
                    nestWithoutTurtles.add(position, withoutTurtle);

                }

                mode.setTitle(userSelection2.size() + " items selected...");
            }

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent it;

        switch (id){
//            case android.R.id.home:
//                it = new Intent(this, ActRecordsMenu.class);
//                startActivityForResult(it, 0);
//                break;

            case R.id.action_home:
                it = new Intent(this, Act_Main.class);
                startActivityForResult(it, 0);
                break;

            case R.id.action_sendData:
                //some operations to send data do central DB
                Toast.makeText(this, "Data sent successfully!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
