package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Activities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivitiesController {
    private SQLiteDatabase connection;

    public ActivitiesController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Activities activities){
        ContentValues contentValues = new ContentValues();

        contentValues.put("activity", activities.getActivity());

        connection.insertOrThrow("activities",null, contentValues);
    }

    public void remove(int idactivity){
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idactivity);

        connection.delete("activities","idactivity = ?",parameters);
    }

    public void edit(Activities activities){
        ContentValues contentValues = new ContentValues();

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(activities.getIdactivity());

        connection.update("activities", contentValues,"idactivity = ?", parameters);
    }

    public List<Activities> fetchAll(){
        List<Activities> activitiesList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idactivity,");
        sql.append("       activity");
        sql.append("  FROM activities;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    Activities activities = new Activities();

                    activities.setIdactivity(result.getInt(result.getColumnIndexOrThrow("idactivity")));
                    activities.setActivity(result.getString(result.getColumnIndexOrThrow("activity")));

                    activitiesList.add(activities);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return activitiesList;
    }

    public Activities fetchOne(int idactivity){
        Activities activities = new Activities();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idactivity,");
        sql.append("       activity");
        sql.append("  FROM activities");
        sql.append("  WHERE idactivity = ?;");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idactivity);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            activities.setIdactivity(result.getInt(result.getColumnIndexOrThrow("idactivity")));
            activities.setActivity(result.getString(result.getColumnIndexOrThrow("activity")));

            return activities;
        }

        return null;
    }
}
