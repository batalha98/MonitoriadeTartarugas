package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.monitoriadetartarugas.domain.entitys.Hatchling;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HatchlingController {
    private Hatchling hatchling;
    private NestController nestController;
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public HatchlingController(SQLiteDatabase connection){
        nestController = new NestController(connection);
        this.connection = connection;
    }

    public void insert(Hatchling hatchling){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", hatchling.getIdnest().getIdnest());
        contentValues.put("dataa", hatchling.getDataa().toString());
        contentValues.put("hatched", hatchling.getHatched());
        contentValues.put("unhatched", hatchling.getUnhatched());
        contentValues.put("died_in_nest", hatchling.getDied_in_nest());
        contentValues.put("alive_in_nest", hatchling.getAlive_in_nest());
        contentValues.put("undeveloped", hatchling.getUndeveloped());
        contentValues.put("predated_eggs", hatchling.getPredated_eggs());
        contentValues.put("description", hatchling.getDescription());

        connection.insertOrThrow("hatchlings",null, contentValues);
    }

    public void remove(int idnest){
        parameters = new String[1];

        parameters[0] = String.valueOf(idnest);

        connection.delete("hatchlings","idnest = ?",parameters);
    }

    public void edit(Hatchling hatchling){
        ContentValues contentValues = new ContentValues();

        contentValues.put("dataa", hatchling.getDataa().toString());
        contentValues.put("hatched", hatchling.getHatched());
        contentValues.put("unhatched", hatchling.getUnhatched());
        contentValues.put("died_in_nest", hatchling.getDied_in_nest());
        contentValues.put("alive_in_nest", hatchling.getAlive_in_nest());
        contentValues.put("undeveloped", hatchling.getUndeveloped());
        contentValues.put("predated_eggs", hatchling.getPredated_eggs());
        contentValues.put("description", hatchling.getDescription());

        parameters = new String[1];

        parameters[0] = String.valueOf(hatchling.getIdnest().getIdnest());

        connection.update("hatchlings", contentValues,"idnest = ?", parameters);
    }

    public List<Hatchling> fetchAll(){
        List<Hatchling> hatchlingList = new ArrayList<>();

        sql = new StringBuilder();
        sql.append("SELECT idnest,");
        sql.append("       dataa,");
        sql.append("       hatched,");
        sql.append("       unhatched,");
        sql.append("       died_in_nest,");
        sql.append("       alive_in_nest,");
        sql.append("       undeveloped,");
        sql.append("       predated_eggs,");
        sql.append("       description");
        sql.append("  FROM hatchlings;");

        Cursor result = connection.rawQuery(sql.toString(), null);

        if(result.getCount() > 0){
            result.moveToFirst();

            do{
                hatchling = new Hatchling();

                hatchling.setIdnest(nestController.fetchOne(
                        result.getInt(result.getColumnIndexOrThrow("idnest"))));
                hatchling.setHatched(
                        result.getInt(result.getColumnIndexOrThrow("hatched")));
                hatchling.setUnhatched(
                        result.getInt(result.getColumnIndexOrThrow("unhatched")));
                hatchling.setAlive_in_nest(
                        result.getInt(result.getColumnIndexOrThrow("alive_in_nest")));
                hatchling.setDied_in_nest(
                        result.getInt(result.getColumnIndexOrThrow("died_in_nest")));
                hatchling.setDataa(
                        new Date(result.getString(result.getColumnIndexOrThrow("dataa"))));
                hatchling.setPredated_eggs(
                        result.getInt(result.getColumnIndexOrThrow("predated_eggs")));
                hatchling.setDescription(
                        result.getString(result.getColumnIndexOrThrow("description")));

                hatchlingList.add(hatchling);
            }while(result.moveToNext());
        }

        return hatchlingList;
    }

    public Hatchling fetchOne(int idnest){
        hatchling = new Hatchling();
        sql = new StringBuilder();

        sql = new StringBuilder();
        sql.append("SELECT idnest,");
        sql.append("       dataa,");
        sql.append("       hatched,");
        sql.append("       unhatched,");
        sql.append("       died_in_nest,");
        sql.append("       alive_in_nest,");
        sql.append("       undeveloped,");
        sql.append("       predated_eggs,");
        sql.append("       description");
        sql.append("  FROM hatchlings");
        sql.append("  WHERE idnest = ?;");

        parameters = new String[1];
        parameters[0] = String.valueOf(idnest);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            hatchling.setIdnest(nestController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idnest"))));
            hatchling.setHatched(
                    result.getInt(result.getColumnIndexOrThrow("hatched")));
            hatchling.setUnhatched(
                    result.getInt(result.getColumnIndexOrThrow("unhatched")));
            hatchling.setAlive_in_nest(
                    result.getInt(result.getColumnIndexOrThrow("alive_in_nest")));
            hatchling.setDied_in_nest(
                    result.getInt(result.getColumnIndexOrThrow("died_in_nest")));
            hatchling.setDataa(
                    new Date(result.getString(result.getColumnIndexOrThrow("dataa"))));
            hatchling.setPredated_eggs(
                    result.getInt(result.getColumnIndexOrThrow("predated_eggs")));
            hatchling.setDescription(
                    result.getString(result.getColumnIndexOrThrow("description")));

            return hatchling;
        }

        return null;
    }
}
