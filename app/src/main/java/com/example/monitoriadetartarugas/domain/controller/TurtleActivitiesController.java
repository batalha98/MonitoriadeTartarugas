package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Activities;
import com.example.monitoriadetartarugas.domain.entitys.Observation;
import com.example.monitoriadetartarugas.domain.entitys.TurtleActivities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurtleActivitiesController {
    private SQLiteDatabase connection;
    private ObservationController observationController;
    private Date dateObservation;

    public TurtleActivitiesController(SQLiteDatabase connection){
        this.observationController = new ObservationController(connection);
        this.connection = connection;
    }

    public TurtleActivitiesController(Date dateObservation){
        this.dateObservation = dateObservation;
    }

    public void insert(TurtleActivities turtleActivities){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idturtle", turtleActivities.getIdturtle().getIdturtle().getIdturtle());
        contentValues.put("beach", turtleActivities.getBeach().getBeach().getBeach());
        contentValues.put("idactivity", turtleActivities.getIdactivity().getIdactivity());

        connection.insertOrThrow("turtleactivities",null, contentValues);
    }

    public void remove(int idturtle, String beach, int idactivity){
        String[] parameters = new String[4];

        parameters[0] = String.valueOf(idturtle);
        parameters[1] = beach;
        parameters[2] = String.valueOf(idactivity);

        connection.delete("turtleactivities","idturtle = ? and beach = ? and idactivity = ?",parameters);
    }

    public void edit(TurtleActivities turtleActivities){
        ContentValues contentValues = new ContentValues();

        String[] parameters = new String[4];

        parameters[0] = String.valueOf(turtleActivities.getIdturtle().getIdturtle().getIdturtle());
        parameters[1] = turtleActivities.getBeach().getBeach().getBeach();
        parameters[2] = String.valueOf(turtleActivities.getIdactivity().getIdactivity());

        connection.update("turtleactivities", contentValues,"idturtle = ? and beach = ? and idactivity = ?", parameters);
    }

    public List<TurtleActivities> fetchAll(){
        List<TurtleActivities> turtleActivitiesList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idturtle,");
        sql.append("       beach,");
        sql.append("       idactivity");
        sql.append("  FROM turtleactivities;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    TurtleActivities turtleActivities = new TurtleActivities();
                    Observation observation = observationController.fetchOne(result.getInt(result.getColumnIndexOrThrow("idturtle"))
                            , result.getString(result.getColumnIndexOrThrow("beach"))
                            , dateObservation);

                    turtleActivities.setIdturtle(observation);
                    turtleActivities.setBeach(observation);
                    turtleActivities.setIdactivity(observation.getIdactivity());

                    turtleActivitiesList.add(turtleActivities);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return turtleActivitiesList;
    }

    public Activities fetchOne(int idturtle, String beach, Date dataa, int idactivity){
        Activities activities = new Activities();
        String[] parameters = new String[4];

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idturtle,");
        sql.append("       beach,");
        sql.append("       idactivity");
        sql.append("  FROM turtleactivities");
        sql.append("  WHERE idturtle = ? and beach = ? and idactivity = ?");

        parameters[0] = String.valueOf(idturtle);
        parameters[1] = beach;
        parameters[2] = String.valueOf(idactivity);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            TurtleActivities turtleActivities = new TurtleActivities();
            Observation observation = observationController.fetchOne(result.getInt(result.getColumnIndexOrThrow("idturtle"))
                    , result.getString(result.getColumnIndexOrThrow("beach"))
                    , dateObservation);

            turtleActivities.setIdturtle(observation);
            turtleActivities.setBeach(observation);
            turtleActivities.setIdactivity(observation.getIdactivity());

            return activities;
        }

        return null;
    }
}
