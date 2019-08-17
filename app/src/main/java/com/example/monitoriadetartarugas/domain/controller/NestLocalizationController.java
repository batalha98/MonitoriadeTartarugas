package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.NestLocalization;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NestLocalizationController {
    private NestLocalization nestLocalization;
    private HabitatController habitatController;
    private BeachController beachController;
    private NestController nestController;
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public NestLocalizationController(SQLiteDatabase connection){
        this.habitatController = new HabitatController(connection);
        this.beachController = new BeachController(connection);
        this.nestController = new NestController(connection);
        this.connection = connection;
    }

    public void insert(NestLocalization nestlocalization){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", nestlocalization.getIdnest().getIdnest());
        contentValues.put("dataa", nestlocalization.getDataa().toString());
        contentValues.put("beach", nestlocalization.getBeach().getBeach());
        contentValues.put("idhabitat", nestlocalization.getIdhabitat().getIdhabitat());
        contentValues.put("distance", nestlocalization.getDistance());
        contentValues.put("height", nestlocalization.getHeight());
        contentValues.put("observations", nestlocalization.getObservations());

        connection.insertOrThrow("nestlocalization",null, contentValues);
    }

    public void remove(int idnest, Date dataa){
        parameters = new String[2];

        parameters[0] = String.valueOf(idnest);
        parameters[1] = dataa.toString();

        connection.delete("nestlocalization","idnest = ? and dataa = ?",parameters);
    }

    public void edit(NestLocalization nestlocalization){
        ContentValues contentValues = new ContentValues();

        contentValues.put("dataa", nestlocalization.getDataa().toString());
        contentValues.put("beach", nestlocalization.getBeach().getBeach());
        contentValues.put("idhabitat", nestlocalization.getIdhabitat().getIdhabitat());
        contentValues.put("distance", nestlocalization.getDistance());
        contentValues.put("height", nestlocalization.getHeight());
        contentValues.put("observations", nestlocalization.getObservations());

        parameters = new String[2];
        parameters[0] = String.valueOf(nestlocalization.getIdnest());
        parameters[1] = nestlocalization.getDataa().toString();

        connection.update("nestlocalization", contentValues,"idnest = ? and dataa = ?", parameters);
    }

    public List<NestLocalization> fetchAll(){
        List<NestLocalization> nestLocalizationList = new ArrayList<>();
        sql = new StringBuilder();

        sql.append("SELECT idnest,");
        sql.append("       dataa,");
        sql.append("       beach,");
        sql.append("       idhabitat,");
        sql.append("       distance,");
        sql.append("       height,");
        sql.append("       observations");
        sql.append("  FROM nestlocalization;");

        Cursor result = connection.rawQuery(sql.toString(), null);

        if(result.getCount() > 0){
            result.moveToFirst();

            do{
                nestLocalization = new NestLocalization();

                nestLocalization.setIdnest(nestController.fetchOne(
                        result.getInt(result.getColumnIndexOrThrow("idnest"))));
                nestLocalization.setIdhabitat(habitatController.fetchOne(
                        result.getInt(result.getColumnIndexOrThrow("idhabitat"))));
                nestLocalization.setBeach(beachController.fetchOne(
                        result.getString(result.getColumnIndexOrThrow("beach"))));
                nestLocalization.setDataa(new Date(
                        result.getString(result.getColumnIndexOrThrow("dataa"))));
                nestLocalization.setDistance(
                        result.getFloat(result.getColumnIndexOrThrow("distance")));
                nestLocalization.setHeight(
                        result.getFloat(result.getColumnIndexOrThrow("height")));
                nestLocalization.setObservations(
                        result.getString(result.getColumnIndexOrThrow("observations")));

                nestLocalizationList.add(nestLocalization);
            }while(result.moveToNext());
        }

        return nestLocalizationList;
    }

    public NestLocalization fetchOne(int idnest, Date dataa){
        nestLocalization = new NestLocalization();
        sql = new StringBuilder();

        sql.append("SELECT idnest,");
        sql.append("       dataa,");
        sql.append("       beach,");
        sql.append("       idhabitat,");
        sql.append("       distance,");
        sql.append("       height,");
        sql.append("       observations");
        sql.append("  FROM nestlocalization");
        sql.append("  WHERE idnest = ? and dataa = ?;");

        parameters = new String[2];
        parameters[0] = String.valueOf(idnest);
        parameters[1] = dataa.toString();

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            nestLocalization.setIdnest(nestController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idnest"))));
            nestLocalization.setIdhabitat(habitatController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idhabitat"))));
            nestLocalization.setBeach(beachController.fetchOne(
                    result.getString(result.getColumnIndexOrThrow("beach"))));
            nestLocalization.setDataa(new Date(
                    result.getString(result.getColumnIndexOrThrow("dataa"))));
            nestLocalization.setDistance(
                    result.getFloat(result.getColumnIndexOrThrow("distance")));
            nestLocalization.setHeight(
                    result.getFloat(result.getColumnIndexOrThrow("height")));
            nestLocalization.setObservations(
                    result.getString(result.getColumnIndexOrThrow("observations")));

            return nestLocalization;
        }

        return null;
    }
}
