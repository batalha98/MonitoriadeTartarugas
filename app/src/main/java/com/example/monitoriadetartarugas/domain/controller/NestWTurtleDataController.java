package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.NestWTurtleData;
import com.example.monitoriadetartarugas.domain.entitys.Specie;
import com.example.monitoriadetartarugas.domain.entitys.TurtleNestData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NestWTurtleDataController {

    private NestWTurtleData nestWTurtleData;
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public NestWTurtleDataController(SQLiteDatabase connection) {
        this.connection = connection;
    }

    public void insert(NestWTurtleData nestWTurtleData){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", nestWTurtleData.getIdnest());
        contentValues.put("specie", nestWTurtleData.getSpecie());
        contentValues.put("habitat", nestWTurtleData.getHabitat());
        contentValues.put("beach", nestWTurtleData.getBeach());
        contentValues.put("gps_east", nestWTurtleData.getGps_east());
        contentValues.put("gps_south", nestWTurtleData.getGps_south());
        contentValues.put("nest_loc_date", nestWTurtleData.getNest_loc_date().toString());
        contentValues.put("depth", nestWTurtleData.getDepth());
        contentValues.put("eggs_quantity", nestWTurtleData.getEggs_quantity());
        contentValues.put("distance_to_tide", nestWTurtleData.getDistance_to_tide());
        contentValues.put("nest_notes", nestWTurtleData.getNest_notes());
        contentValues.put("hatched", nestWTurtleData.getHatched());
        contentValues.put("hatch_dataa", nestWTurtleData.getHatch_dataa().toString());
        contentValues.put("died_in_nest", nestWTurtleData.getDied_in_nest());
        contentValues.put("alive_in_nest", nestWTurtleData.getAlive_in_nest());
        contentValues.put("undeveloped", nestWTurtleData.getUndeveloped());
        contentValues.put("predated_eggs", nestWTurtleData.getPredated_eggs());
        contentValues.put("unhatched", nestWTurtleData.getUnhatched());
        contentValues.put("hatch_notes", nestWTurtleData.getHatch_notes());

        connection.insertOrThrow("nestWTurtleData",null, contentValues);
    }

    public void remove(int idnest, Specie specie){
        String[] parameters = new String[2];

        parameters[0] = String.valueOf(idnest);
        parameters[1] = specie.getSpecie();

        connection.delete("nestWTurtleData","idnest = ? and specie = ?",parameters);
    }

    public void edit(NestWTurtleData nestWTurtleData){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", nestWTurtleData.getIdnest());
        contentValues.put("specie", nestWTurtleData.getSpecie());
        contentValues.put("habitat", nestWTurtleData.getHabitat());
        contentValues.put("beach", nestWTurtleData.getBeach());
        contentValues.put("gps_east", nestWTurtleData.getGps_east());
        contentValues.put("gps_south", nestWTurtleData.getGps_south());
        contentValues.put("nest_loc_date", nestWTurtleData.getNest_loc_date().toString());
        contentValues.put("depth", nestWTurtleData.getDepth());
        contentValues.put("eggs_quantity", nestWTurtleData.getEggs_quantity());
        contentValues.put("distance_to_tide", nestWTurtleData.getDistance_to_tide());
        contentValues.put("nest_notes", nestWTurtleData.getNest_notes());
        contentValues.put("hatched", nestWTurtleData.getHatched());
        contentValues.put("hatch_dataa", nestWTurtleData.getHatch_dataa().toString());
        contentValues.put("died_in_nest", nestWTurtleData.getDied_in_nest());
        contentValues.put("alive_in_nest", nestWTurtleData.getAlive_in_nest());
        contentValues.put("undeveloped", nestWTurtleData.getUndeveloped());
        contentValues.put("predated_eggs", nestWTurtleData.getPredated_eggs());
        contentValues.put("unhatched", nestWTurtleData.getUnhatched());
        contentValues.put("hatch_notes", nestWTurtleData.getHatch_notes());

        parameters = new String[2];

        parameters[0] = String.valueOf(nestWTurtleData.getIdnest());
        parameters[1] = String.valueOf(nestWTurtleData.getSpecie());

        connection.update("nestWTurtleData", contentValues,"idnest = ? and specie = ?",parameters);
    }

    public List<NestWTurtleData> getAll(){
        List<NestWTurtleData> data = new ArrayList<>();

        sql = new StringBuilder();
        sql.append("SELECT * FROM nestWTurtleData;");

        Cursor cursor = connection.rawQuery(sql.toString(),null);

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                nestWTurtleData = new NestWTurtleData();

                nestWTurtleData.setIdnest(cursor.getInt(cursor.getColumnIndexOrThrow("idnest")));
                nestWTurtleData.setSpecie(cursor.getString(cursor.getColumnIndexOrThrow("specie")));
                nestWTurtleData.setHatched(cursor.getInt(cursor.getColumnIndexOrThrow("hatched")));
                nestWTurtleData.setDied_in_nest(cursor.getInt(cursor.getColumnIndexOrThrow("died_in_nest")));
                nestWTurtleData.setAlive_in_nest(cursor.getInt(cursor.getColumnIndexOrThrow("alive_in_nest")));
                nestWTurtleData.setUndeveloped(cursor.getInt(cursor.getColumnIndexOrThrow("undeveloped")));
                nestWTurtleData.setPredated_eggs(cursor.getInt(cursor.getColumnIndexOrThrow("predated_eggs")));
                nestWTurtleData.setHatch_dataa(new Date(cursor.getString(cursor.getColumnIndexOrThrow("hatch_dataa"))));
                nestWTurtleData.setNest_loc_date(new Date(cursor.getString(cursor.getColumnIndexOrThrow("nest_loc_date"))));
                nestWTurtleData.setBeach(cursor.getString(cursor.getColumnIndexOrThrow("beach")));
                nestWTurtleData.setHabitat(cursor.getString(cursor.getColumnIndexOrThrow("habitat")));
                nestWTurtleData.setGps_east(cursor.getFloat(cursor.getColumnIndexOrThrow("gps_east")));
                nestWTurtleData.setGps_south(cursor.getFloat(cursor.getColumnIndexOrThrow("gps_south")));
                nestWTurtleData.setUnhatched(cursor.getInt(cursor.getColumnIndexOrThrow("unhatched")));
                nestWTurtleData.setDepth(cursor.getInt(cursor.getColumnIndexOrThrow("depth")));
                nestWTurtleData.setEggs_quantity(cursor.getInt(cursor.getColumnIndexOrThrow("eggs_quantity")));
                nestWTurtleData.setDistance_to_tide(cursor.getFloat(cursor.getColumnIndexOrThrow("distance_to_tide")));
                nestWTurtleData.setHatch_notes(cursor.getString(cursor.getColumnIndexOrThrow("nest_notes")));
                nestWTurtleData.setNest_notes(cursor.getString(cursor.getColumnIndexOrThrow("hatch_notes")));

                data.add(nestWTurtleData);
            }
        }
        return data;
    }

    public NestWTurtleData getOne(int idnest, Specie specie){
        sql = new StringBuilder();
        sql.append("SELECT * FROM nestWTurtleData;");
        sql.append("WHERE idnest = ? AND specie = ?;");

        parameters = new String[2];
        parameters[0] = String.valueOf(idnest);
        parameters[1] = specie.getSpecie();

        Cursor cursor = connection.rawQuery(sql.toString(),parameters);

        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            nestWTurtleData = new NestWTurtleData();

            nestWTurtleData.setIdnest(cursor.getInt(cursor.getColumnIndexOrThrow("idnest")));
            nestWTurtleData.setSpecie(cursor.getString(cursor.getColumnIndexOrThrow("specie")));
            nestWTurtleData.setHatched(cursor.getInt(cursor.getColumnIndexOrThrow("hatched")));
            nestWTurtleData.setDied_in_nest(cursor.getInt(cursor.getColumnIndexOrThrow("died_in_nest")));
            nestWTurtleData.setAlive_in_nest(cursor.getInt(cursor.getColumnIndexOrThrow("alive_in_nest")));
            nestWTurtleData.setUndeveloped(cursor.getInt(cursor.getColumnIndexOrThrow("undeveloped")));
            nestWTurtleData.setPredated_eggs(cursor.getInt(cursor.getColumnIndexOrThrow("predated_eggs")));
            nestWTurtleData.setHatch_dataa(new Date(cursor.getString(cursor.getColumnIndexOrThrow("hatch_dataa"))));
            nestWTurtleData.setNest_loc_date(new Date(cursor.getString(cursor.getColumnIndexOrThrow("nest_loc_date"))));
            nestWTurtleData.setBeach(cursor.getString(cursor.getColumnIndexOrThrow("beach")));
            nestWTurtleData.setHabitat(cursor.getString(cursor.getColumnIndexOrThrow("habitat")));
            nestWTurtleData.setGps_east(cursor.getFloat(cursor.getColumnIndexOrThrow("gps_east")));
            nestWTurtleData.setGps_south(cursor.getFloat(cursor.getColumnIndexOrThrow("gps_south")));
            nestWTurtleData.setUnhatched(cursor.getInt(cursor.getColumnIndexOrThrow("unhatched")));
            nestWTurtleData.setDepth(cursor.getInt(cursor.getColumnIndexOrThrow("depth")));
            nestWTurtleData.setEggs_quantity(cursor.getInt(cursor.getColumnIndexOrThrow("eggs_quantity")));
            nestWTurtleData.setDistance_to_tide(cursor.getFloat(cursor.getColumnIndexOrThrow("distance_to_tide")));
            nestWTurtleData.setHatch_notes(cursor.getString(cursor.getColumnIndexOrThrow("nest_notes")));
            nestWTurtleData.setNest_notes(cursor.getString(cursor.getColumnIndexOrThrow("hatch_notes")));

            return nestWTurtleData;
        }

        return null;
    }
}
