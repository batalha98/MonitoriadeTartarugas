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
        contentValues.put("nest_marking_date", nestlocalization.getNest_marking_date().toString());
        contentValues.put("beach", nestlocalization.getBeach().getBeach());
        contentValues.put("gps_east", nestlocalization.getGpsEast());
        contentValues.put("gps_south", nestlocalization.getGpsSouth());
        contentValues.put("idhabitat", nestlocalization.getIdhabitat().getIdhabitat());
        contentValues.put("notes", nestlocalization.getNotes());

        connection.insertOrThrow("nestlocalization",null, contentValues);
    }

    public void remove(int idnest, Date nest_marking_date){
        parameters = new String[2];

        parameters[0] = String.valueOf(idnest);
        parameters[1] = nest_marking_date.toString();

        connection.delete("nestlocalization","idnest = ? and nest_marking_date = ?",parameters);
    }

    public void edit(NestLocalization nestlocalization){
        ContentValues contentValues = new ContentValues();

        contentValues.put("nest_marking_date", nestlocalization.getNest_marking_date().toString());
        contentValues.put("beach", nestlocalization.getBeach().getBeach());
        contentValues.put("gps_east", nestlocalization.getGpsEast());
        contentValues.put("gps_south", nestlocalization.getGpsSouth());
        contentValues.put("idhabitat", nestlocalization.getIdhabitat().getIdhabitat());
        contentValues.put("notes", nestlocalization.getNotes());

        parameters = new String[2];
        parameters[0] = String.valueOf(nestlocalization.getIdnest());
        parameters[1] = nestlocalization.getNest_marking_date().toString();

        connection.update("nestlocalization", contentValues,"idnest = ? and nest_marking_date = ?", parameters);
    }

    public List<NestLocalization> fetchAll(){
        List<NestLocalization> nestLocalizationList = new ArrayList<>();
        sql = new StringBuilder();

        sql.append("SELECT idnest,");
        sql.append("       nest_marking_date,");
        sql.append("       beach,");
        sql.append("       gps_east,");
        sql.append("       gps_south,");
        sql.append("       idhabitat,");
        sql.append("       notes");
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
                nestLocalization.setGpsEast(
                        result.getFloat(result.getColumnIndexOrThrow("gps_east")));
                nestLocalization.setGpsSouth(
                        result.getFloat(result.getColumnIndexOrThrow("gps_south")));
                nestLocalization.setNest_marking_date(new Date(
                        result.getString(result.getColumnIndexOrThrow("nest_marking_date"))));
                nestLocalization.setNotes(
                        result.getString(result.getColumnIndexOrThrow("notes")));

                nestLocalizationList.add(nestLocalization);
            }while(result.moveToNext());
        }

        return nestLocalizationList;
    }

    public NestLocalization fetchOne(int idnest, Date nest_marking_date){
        nestLocalization = new NestLocalization();
        sql = new StringBuilder();

        sql.append("SELECT idnest,");
        sql.append("       nest_marking_date,");
        sql.append("       beach,");
        sql.append("       gps_east,");
        sql.append("       gps_south,");
        sql.append("       idhabitat,");
        sql.append("       notes");
        sql.append("  FROM nestlocalization");
        sql.append("  WHERE idnest = ? and nest_marking_date = ?;");

        parameters = new String[2];
        parameters[0] = String.valueOf(idnest);
        parameters[1] = nest_marking_date.toString();

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            nestLocalization.setIdnest(nestController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idnest"))));
            nestLocalization.setIdhabitat(habitatController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idhabitat"))));
            nestLocalization.setBeach(beachController.fetchOne(
                    result.getString(result.getColumnIndexOrThrow("beach"))));
            nestLocalization.setGpsEast(
                    result.getFloat(result.getColumnIndexOrThrow("gps_east")));
            nestLocalization.setGpsSouth(
                    result.getFloat(result.getColumnIndexOrThrow("gps_south")));
            nestLocalization.setNest_marking_date(new Date(
                    result.getString(result.getColumnIndexOrThrow("nest_marking_date"))));
            nestLocalization.setNotes(
                    result.getString(result.getColumnIndexOrThrow("notes")));

            return nestLocalization;
        }

        return null;
    }
}
