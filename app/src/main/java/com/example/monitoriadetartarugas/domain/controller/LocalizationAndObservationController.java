package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.LocalizationAndObservation;
import com.example.monitoriadetartarugas.domain.entitys.Observation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalizationAndObservationController {
    private LocalizationAndObservation localizationAndObservation;
    private NestLocalizationController nestLocalizationController;
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public LocalizationAndObservationController(SQLiteDatabase connection){
        nestLocalizationController = new NestLocalizationController(connection);
        this.connection = connection;
    }

    public void insert(LocalizationAndObservation localizationAndObservation){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", localizationAndObservation.getIdnest().getIdnest().getIdnest());
        contentValues.put("nest_marking_date", localizationAndObservation.getNest_marking_date().toString());
        contentValues.put("observation_date", localizationAndObservation.getObservation_date().toString());

        connection.insertOrThrow("localizationAndObservation",null, contentValues);
    }

    public void remove(int idnest, Date nest_marking_date, Date observation_date){
        parameters = new String[3];

        parameters[0] = String.valueOf(idnest);
        parameters[1] = nest_marking_date.toString();
        parameters[2] = observation_date.toString();

        connection.delete("localizationAndObservation","idnest = ? and nest_marking_date = ? and observation_date = ?",parameters);
    }

    public void edit(LocalizationAndObservation localizationAndObservation){
        ContentValues contentValues = new ContentValues();
        parameters = new String[3];

        contentValues.put("idnest", localizationAndObservation.getIdnest().getIdnest().getIdnest());
        contentValues.put("nest_marking_date", localizationAndObservation.getNest_marking_date().toString());
        contentValues.put("observation_date", localizationAndObservation.getObservation_date().toString());

        parameters[0] = String.valueOf(localizationAndObservation.getIdnest().getIdnest().getIdnest());
        parameters[1] = localizationAndObservation.getNest_marking_date().toString();
        parameters[2] = localizationAndObservation.getObservation_date().toString();

        connection.update("localizationAndObservation", contentValues,"idnest = ? and nest_marking_date = ? and observation_date = ?", parameters);
    }

    public List<LocalizationAndObservation> fetchAll(){
        List<LocalizationAndObservation> localizationAndObservationList = new ArrayList<>();
        sql = new StringBuilder();

        sql.append("SELECT idnest,");
        sql.append("       nest_marking_date,");
        sql.append("       observation_date");
        sql.append("  FROM localizationAndObservation;");

        Cursor result = connection.rawQuery(sql.toString(), null);

        if(result.getCount() > 0){
            result.moveToFirst();

            do{
                localizationAndObservation = new LocalizationAndObservation();

                localizationAndObservation.setIdnest(
                        nestLocalizationController.fetchOne(result.getInt(result.getColumnIndexOrThrow("idnest"))
                                , new Date(result.getString(result.getColumnIndexOrThrow("nest_marking_date")))));
                localizationAndObservation.setNest_marking_date(
                        nestLocalizationController.fetchOne(result.getInt(result.getColumnIndexOrThrow("idnest"))
                                , new Date(result.getString(result.getColumnIndexOrThrow("nest_marking_date")))));
                localizationAndObservation.setObservation_date(
                        new Date(result.getString(result.getColumnIndexOrThrow("observation_date"))));

                localizationAndObservationList.add(localizationAndObservation);
            }while(result.moveToNext());
        }

        return localizationAndObservationList;
    }

    public LocalizationAndObservation fetchOne(int idnest, Date nest_marking_date, Date observation_date){
        localizationAndObservation = new LocalizationAndObservation();
        sql = new StringBuilder();
        parameters = new String[3];

        sql.append("SELECT idnest,");
        sql.append("       nest_marking_date,");
        sql.append("       observation_date");
        sql.append("  FROM localizationAndObservation");
        sql.append("  WHERE idnest = ? and nest_marking_date = ? and observation_date = ?;");

        parameters[0] = String.valueOf(idnest);
        parameters[1] = nest_marking_date.toString();
        parameters[2] = observation_date.toString();

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            localizationAndObservation.setIdnest(
                    nestLocalizationController.fetchOne(result.getInt(result.getColumnIndexOrThrow("idnest"))
                            , new Date(result.getString(result.getColumnIndexOrThrow("nest_marking_date")))));
            localizationAndObservation.setNest_marking_date(
                    nestLocalizationController.fetchOne(result.getInt(result.getColumnIndexOrThrow("idnest"))
                            , new Date(result.getString(result.getColumnIndexOrThrow("nest_marking_date")))));
            localizationAndObservation.setObservation_date(
                    new Date(result.getString(result.getColumnIndexOrThrow("observation_date"))));

            return localizationAndObservation;
        }

        return null;
    }
}
