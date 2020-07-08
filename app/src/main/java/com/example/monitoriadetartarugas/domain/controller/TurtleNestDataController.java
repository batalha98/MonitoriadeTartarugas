package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.TurtleNestData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurtleNestDataController {

    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;
    TurtleNestData turtleNestData;

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
        contentValues.put("dune_height",turtleNestData.getDune_height());
        contentValues.put("ccl_measure",turtleNestData.getCcl_measure());
        contentValues.put("cwl_measure",turtleNestData.getCwl_measure());
        contentValues.put("gps_east",turtleNestData.getGps_east());
        contentValues.put("gps_south",turtleNestData.getGps_south());
        contentValues.put("depth",turtleNestData.getDepth());
        contentValues.put("eggs_quantity",turtleNestData.getEggs_quantity());
        contentValues.put("distance_to_tide",turtleNestData.getDistance_to_tide());

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
        contentValues.put("ccl_measure",turtleNestData.getCcl_measure());
        contentValues.put("cwl_measure",turtleNestData.getCwl_measure());
        contentValues.put("gps_east",turtleNestData.getGps_east());
        contentValues.put("gps_south",turtleNestData.getGps_south());
        contentValues.put("unhatched",turtleNestData.getUnhatched());
        parameters = new String[2];

        parameters[0] = String.valueOf(turtleNestData.getIdturtle());
        parameters[1] = String.valueOf(turtleNestData.getIdnest());


        connection.update("turtlenestdata", contentValues,"idturtle = ? and idnest = ?",parameters);
    }

    public List<TurtleNestData> getAll(){
        List<TurtleNestData> data = new ArrayList<>();

        sql = new StringBuilder();
        sql.append("SELECT * FROM turtlenestdata;");


            Cursor cursor = connection.rawQuery(sql.toString(),null);

            if (cursor.getCount()>0){
                cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                turtleNestData = new TurtleNestData();

                turtleNestData.setIdnest(cursor.getInt(cursor.getColumnIndexOrThrow("idturtle")));
                turtleNestData.setIdnest(cursor.getInt(cursor.getColumnIndexOrThrow("idnest")));
                turtleNestData.setHatched(cursor.getInt(cursor.getColumnIndexOrThrow("hatched")));
                turtleNestData.setDied_in_nest(cursor.getInt(cursor.getColumnIndexOrThrow("died_in_nest")));
                turtleNestData.setAlive_in_nest(cursor.getInt(cursor.getColumnIndexOrThrow("alive_in_nest")));
                turtleNestData.setUndeveloped(cursor.getInt(cursor.getColumnIndexOrThrow("undeveloped")));
                turtleNestData.setPredated_eggs(cursor.getInt(cursor.getColumnIndexOrThrow("predated_eggs")));
                turtleNestData.setLeftring(cursor.getInt(cursor.getColumnIndexOrThrow("leftring")));
                turtleNestData.setRightring(cursor.getInt(cursor.getColumnIndexOrThrow("rightring")));
                turtleNestData.setInternal_tag(cursor.getInt(cursor.getColumnIndexOrThrow("internal_tag")));
                turtleNestData.setHatch_dataa(new Date(cursor.getString(cursor.getColumnIndexOrThrow("hatch_dataa"))));
                turtleNestData.setNest_marking_date(new Date(cursor.getString(cursor.getColumnIndexOrThrow("nest_marking_date"))));
                turtleNestData.setTagg_dataa(new Date(cursor.getString(cursor.getColumnIndexOrThrow("tagg_dataa"))));
                turtleNestData.setObs_dataa(new Date(cursor.getString(cursor.getColumnIndexOrThrow("obs_dataa"))));
                turtleNestData.setNest_beach(cursor.getString(cursor.getColumnIndexOrThrow("nest_beach")));
                turtleNestData.setHabitat(cursor.getString(cursor.getColumnIndexOrThrow("habitat")));
                turtleNestData.setObs_beach(cursor.getString(cursor.getColumnIndexOrThrow("obs_beach")));
                turtleNestData.setActivity(cursor.getString(cursor.getColumnIndexOrThrow("activity")));
                turtleNestData.setWc(cursor.getString(cursor.getColumnIndexOrThrow("wc")));
                turtleNestData.setWd(cursor.getString(cursor.getColumnIndexOrThrow("wd")));
                turtleNestData.setCcl_measure(cursor.getDouble(cursor.getColumnIndexOrThrow("ccl_measure")));
                turtleNestData.setCwl_measure(cursor.getDouble(cursor.getColumnIndexOrThrow("cwl_measure")));
                turtleNestData.setGps_east(cursor.getFloat(cursor.getColumnIndexOrThrow("gps_east")));
                turtleNestData.setGps_south(cursor.getFloat(cursor.getColumnIndexOrThrow("gps_south")));
                turtleNestData.setUnhatched(cursor.getInt(cursor.getColumnIndexOrThrow("unhatched")));

                data.add(turtleNestData);
            }
        }
        return data;
    }


    public TurtleNestData getOne(int idturtle,int idnest){

        sql = new StringBuilder();
        sql.append("SELECT * FROM turtlenestdata");
        sql.append("WHERE idturtle = ? AND idnest = ?;");

        parameters = new String[2];
        parameters[0] = String.valueOf(idturtle);
        parameters[1] = String.valueOf(idnest);

        Cursor cursor = connection.rawQuery(sql.toString(),parameters);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
                turtleNestData = new TurtleNestData();

                turtleNestData.setIdnest(cursor.getInt(cursor.getColumnIndexOrThrow("idturtle")));
                turtleNestData.setIdnest(cursor.getInt(cursor.getColumnIndexOrThrow("idnest")));
                turtleNestData.setHatched(cursor.getInt(cursor.getColumnIndexOrThrow("hatched")));
                turtleNestData.setDied_in_nest(cursor.getInt(cursor.getColumnIndexOrThrow("died_in_nest")));
                turtleNestData.setAlive_in_nest(cursor.getInt(cursor.getColumnIndexOrThrow("alive_in_nest")));
                turtleNestData.setUndeveloped(cursor.getInt(cursor.getColumnIndexOrThrow("undeveloped")));
                turtleNestData.setPredated_eggs(cursor.getInt(cursor.getColumnIndexOrThrow("predated_eggs")));
                turtleNestData.setLeftring(cursor.getInt(cursor.getColumnIndexOrThrow("leftring")));
                turtleNestData.setRightring(cursor.getInt(cursor.getColumnIndexOrThrow("rightring")));
                turtleNestData.setInternal_tag(cursor.getInt(cursor.getColumnIndexOrThrow("internal_tag")));
                turtleNestData.setHatch_dataa(new Date(cursor.getString(cursor.getColumnIndexOrThrow("hatch_dataa"))));
                turtleNestData.setNest_marking_date(new Date(cursor.getString(cursor.getColumnIndexOrThrow("nest_marking_date"))));
                turtleNestData.setTagg_dataa(new Date(cursor.getString(cursor.getColumnIndexOrThrow("tagg_dataa"))));
                turtleNestData.setObs_dataa(new Date(cursor.getString(cursor.getColumnIndexOrThrow("obs_dataa"))));
                turtleNestData.setNest_beach(cursor.getString(cursor.getColumnIndexOrThrow("nest_beach")));
                turtleNestData.setHabitat(cursor.getString(cursor.getColumnIndexOrThrow("habitat")));
                turtleNestData.setObs_beach(cursor.getString(cursor.getColumnIndexOrThrow("obs_beach")));
                turtleNestData.setActivity(cursor.getString(cursor.getColumnIndexOrThrow("activity")));
                turtleNestData.setWc(cursor.getString(cursor.getColumnIndexOrThrow("wc")));
                turtleNestData.setWd(cursor.getString(cursor.getColumnIndexOrThrow("wd")));

                return turtleNestData;

            }
                return null;
    }

}