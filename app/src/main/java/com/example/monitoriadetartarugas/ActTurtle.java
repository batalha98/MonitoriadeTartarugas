package com.example.monitoriadetartarugas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.monitoriadetartarugas.database.DataOpenHelper;
import com.example.monitoriadetartarugas.domain.controller.SpecieController;
import com.example.monitoriadetartarugas.domain.controller.TurtleController;
import com.example.monitoriadetartarugas.domain.entitys.Specie;
import com.example.monitoriadetartarugas.domain.entitys.Turtle;

import java.util.List;

public class ActTurtle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private long idturtle;
    private Spinner spinner_specie;
    private EditText txt_description;
    private TurtleController turtleController;
    private SpecieController specieController;
    private List<Specie> speciesList;
    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_turtle);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_description = findViewById(R.id.txt_description);
        spinner_specie = findViewById(R.id.spinner_specie);

        spinner_specie.setOnItemSelectedListener(this);

        createConnection();
        getSpinnerValues();
    }

    private void getSpinnerValues(){
        speciesList = specieController.fetchAll();

        ArrayAdapter<Specie> specieAdapter = new ArrayAdapter<Specie>(this
                , android.R.layout.simple_spinner_item
                , speciesList);

        specieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_specie.setAdapter(specieAdapter);
    }

    private void confirm(){
        try {
            Turtle turtle = new Turtle();
            Specie specie = (Specie) spinner_specie.getSelectedItem();

            String description = txt_description.getText().toString();

            turtle.setIdspecie(specieController.fetchOne(specie.getIdspecie()));
            turtle.setDescription(description);

            idturtle = turtleController.insert(turtle);
        }catch (Exception e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    public void createConnection(){
        try {
            dataOpenHelper = new DataOpenHelper(this);

            connection = dataOpenHelper.getWritableDatabase();

            turtleController = new TurtleController(connection);
            specieController = new SpecieController(connection);

        }catch(SQLException e){
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.title_msgErro);
            alertDialog.setMessage(e.getMessage());
            alertDialog.setNeutralButton("OK", null);
            alertDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_register_turtle_and_nest, menu);

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
                confirm();
                Intent it = new Intent(this, ActObservation.class);

                Bundle parameters = new Bundle();
                parameters.putSerializable("turtle", turtleController.fetchOne((int) idturtle));
                it.putExtras(parameters);

                startActivityForResult(it, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
