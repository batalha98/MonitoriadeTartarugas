package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Hatchling;
import com.example.monitoriadetartarugas.domain.entitys.Observation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HatchlingController {
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

//    public HatchlingController(SQLiteDatabase connection){
//        this.connection = connection;
//    }
//
//    public void insert(Hatchling hatchling){
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put("idnest", hatchling.getIdnest().toString());
//        contentValues.put("dataa", hatchling.getDataa().toString());
//        contentValues.put("hatched", hatchling.getHatched());
//        contentValues.put("unhatched", hatchling.getUnhatched());
//        contentValues.put("died_in_nest", hatchling.getDied_in_nest());
//        contentValues.put("alive_in_nest", hatchling.getAlive_in_nest());
//        contentValues.put("undeveloped", hatchling.getUndeveloped());
//        contentValues.put("predated_eggs", hatchling.getPredated_eggs());
//        contentValues.put("description", hatchling.getDescription());
//
//        connection.insertOrThrow("hatchlings",null, contentValues);
//    }
//
//    public void remove(int idnest){
//        parameters = new String[1];
//
//        parameters[0] = String.valueOf(idnest);
//
//        connection.delete("hatchlings","idnest = ?",parameters);
//    }
//
//    public void edit(Hatchling hatchling){
//        ContentValues contentValues = new ContentValues();
//
//        parameters = new String[1];
//
//        parameters[0] = String.valueOf(hatchling.getIdnest().getIdnest());
//
//        connection.update("hatchlings", contentValues,"idnest = ?", parameters);
//    }
//
//    public List<Observation> fetchAll(){
//        List<Observation> observationList = new ArrayList<>();
//        turtleController = new TurtleController(connection);
//        activitiesController = new ActivitiesController(connection);
//        beachController = new BeachController(connection);
//        windCategoryController = new WindCategoryController(connection);
//        windDirectionController = new WindDirectionController(connection);
//
//        sql = new StringBuilder();
//        sql.append("SELECT idturtle,");
//        sql.append("       beach,");
//        sql.append("       dataa,");
//        sql.append("       wc,");
//        sql.append("       wd,");
//        sql.append("       idactivity,");
//        sql.append("       beach_height,");
//        sql.append("       beach_time,");
//        sql.append("  FROM observation;");
//        try {
//            Cursor result = connection.rawQuery(sql.toString(), null);
//
//            if(result.getCount() > 0){
//                result.moveToFirst();
//
//                do{
//                    observation = new Observation();
//
//                    observation.setIdturtle(turtleController.fetchOne(result.getColumnIndexOrThrow("idturtle")));
//                    observation.setIdactivity(activitiesController.fetchOne(result.getColumnIndexOrThrow("idactivity")));
//                    observation.setBeach(beachController.fetchOne(String.valueOf(result.getColumnIndexOrThrow("beach"))));
//                    observation.setBeach_height((float)result.getColumnIndexOrThrow("beach_height"));
//                    observation.setBeach_time(new Date(result.getColumnIndexOrThrow("beach_time")));
//                    observation.setWc(windCategoryController.fetchOne(result.getColumnIndexOrThrow("wc")));
//                    observation.setWd(windDirectionController.fetchOne(result.getColumnIndexOrThrow("wd")));
//                    observation.setDataa(new Date(result.getColumnIndexOrThrow("dataa")));
//
//                    observationList.add(observation);
//                }while(result.moveToNext());
//            }
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }
//
//        return observationList;
//    }
//
//    public Observation fetchOne(int idturtle, String beach, Date dataa){
//        observation = new Observation();
//        sql = new StringBuilder();
//        turtleController = new TurtleController(connection);
//        activitiesController = new ActivitiesController(connection);
//        beachController = new BeachController(connection);
//        windCategoryController = new WindCategoryController(connection);
//        windDirectionController = new WindDirectionController(connection);
//
//        sql.append("SELECT idturtle,");
//        sql.append("       beach,");
//        sql.append("       dataa,");
//        sql.append("       wc,");
//        sql.append("       wd,");
//        sql.append("       idactivity,");
//        sql.append("       beach_height,");
//        sql.append("       beach_time,");
//        sql.append("  FROM observation;");
//        sql.append("  WHERE idturtle = ? and beach = ? and dataa = ?;");
//
//        parameters = new String[3];
//        parameters[0] = String.valueOf(idturtle);
//        parameters[1] = beach;
//        parameters[2] = String.valueOf(dataa);
//
//        Cursor result = connection.rawQuery(sql.toString(), null);
//
//        if(result.getCount() > 0){
//            result.moveToFirst();
//
//            observation.setIdturtle(turtleController.fetchOne(result.getColumnIndexOrThrow("idturtle")));
//            observation.setIdactivity(activitiesController.fetchOne(result.getColumnIndexOrThrow("idactivity")));
//            observation.setBeach(beachController.fetchOne(String.valueOf(result.getColumnIndexOrThrow("beach"))));
//            observation.setBeach_height((float)result.getColumnIndexOrThrow("beach_height"));
//            observation.setBeach_time(new Date(result.getColumnIndexOrThrow("beach_time")));
//            observation.setWc(windCategoryController.fetchOne(result.getColumnIndexOrThrow("wc")));
//            observation.setWd(windDirectionController.fetchOne(result.getColumnIndexOrThrow("wd")));
//            observation.setDataa(new Date(result.getColumnIndexOrThrow("dataa")));
//
//            return observation;
//        }
//
//        return null;
//    }
}
