package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.TurtleNestData;

import java.util.Date;

public class TurtleNestDataController {

    private SQLiteDatabase connection;
    private String[] parameters;

    public TurtleNestDataController(SQLiteDatabase connection) {
        this.connection = connection;
    }

    public void insert(TurtleNestData turtleNestData){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idturtle",turtleNestData.getIdturtle());
        contentValues.put("idnest",turtleNestData.getIdnest());
        contentValues.put("hatched",turtleNestData.getHatched());
        contentValues.put("died_in_nest",turtleNestData.getDied_in_nest());
        contentValues.put("alive_in_nest",turtleNestData.getAlive_in_nest());
        contentValues.put("undeveloped",turtleNestData.getUndeveloped());
        contentValues.put("predated_eggs",turtleNestData.getPredated_eggs());
        contentValues.put("leftring",turtleNestData.getLeftring());
        contentValues.put("rightring",turtleNestData.getRightring());
        contentValues.put("internal_tag",turtleNestData.getInternal_tag());
        contentValues.put("hatch_dataa",turtleNestData.getHatch_dataa().toString());
        contentValues.put("nest_marking_date",turtleNestData.getNest_marking_date().toString());
        contentValues.put("tagg_dataa",turtleNestData.getTagg_dataa().toString());
        contentValues.put("obs_dataa",turtleNestData.getObs_dataa().toString());
        contentValues.put("nest_beach",turtleNestData.getNest_beach());
        contentValues.put("habitat",turtleNestData.getHabitat());
        contentValues.put("obs_beach",turtleNestData.getObs_beach());
        contentValues.put("activity", turtleNestData.getActivity());
        contentValues.put("wc",turtleNestData.getWc());
        contentValues.put("wd",turtleNestData.getWd());

        connection.insertOrThrow("turtlenestdata",null, contentValues);
    }

    public void remove(int idturtle, int idnest){
        String[] parameters = new String[2];

        parameters[0] = String.valueOf(idturtle);
        parameters[1] = String.valueOf(idnest);

        connection.delete("turtlenestdata","idturtle = ? and idnest = ?",parameters);
    }

    public void edit(TurtleNestData turtleNestData){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idturtle",turtleNestData.getIdturtle());
        contentValues.put("idnest",turtleNestData.getIdnest());
        contentValues.put("hatched",turtleNestData.getHatched());
        contentValues.put("died_in_nest",turtleNestData.getDied_in_nest());
        contentValues.put("alive_in_nest",turtleNestData.getAlive_in_nest());
        contentValues.put("undeveloped",turtleNestData.getUndeveloped());
        contentValues.put("predated_eggs",turtleNestData.getPredated_eggs());
        contentValues.put("leftring",turtleNestData.getLeftring());
        contentValues.put("rightring",turtleNestData.getRightring());
        contentValues.put("internal_tag",turtleNestData.getInternal_tag());
        contentValues.put("hatch_dataa",turtleNestData.getHatch_dataa().toString());
        contentValues.put("nest_marking_date",turtleNestData.getNest_marking_date().toString());
        contentValues.put("tagg_dataa",turtleNestData.getTagg_dataa().toString());
        contentValues.put("obs_dataa",turtleNestData.getObs_dataa().toString());
        contentValues.put("nest_beach",turtleNestData.getNest_beach());
        contentValues.put("habitat",turtleNestData.getHabitat());
        contentValues.put("obs_beach",turtleNestData.getObs_beach());
        contentValues.put("activity", turtleNestData.getActivity());
        contentValues.put("wc",turtleNestData.getWc());
        contentValues.put("wd",turtleNestData.getWd());

        parameters = new String[2];

        parameters[0] = String.valueOf(turtleNestData.getIdturtle());
        parameters[1] = String.valueOf(turtleNestData.getIdnest());


        connection.update("turtlenestdata", contentValues,"idturtle = ? and idnest = ?",parameters);
    }

}
