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

    public void insert(Activities activity){
        ContentValues contentValues = new ContentValues();

        contentValues.put("activity", activity.getActivity());

        connection.insertOrThrow("activities",null, contentValues);
    }

    public void remove(String activity){
        String[] parameters = new String[1];
        parameters[0] = activity;

        connection.delete("activities","activity = ?",parameters);
    }

    public void edit(String old_activity, String new_activity){
        ContentValues contentValues = new ContentValues();

        contentValues.put("activity", new_activity);

        String[] parameters = new String[1];
        parameters[0] = old_activity;

        connection.update("activities", contentValues,"activity = ?", parameters);
    }

    public List<Activities> fetchAll(){
        List<Activities> activitiesList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM activities;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    Activities activities = new Activities();

                    activities.setActivity(result.getString(result.getColumnIndexOrThrow("activity")));

                    activitiesList.add(activities);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return activitiesList;
    }

    public Activities fetchOne(String activity){
        Activities activities = new Activities();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM activities");
        sql.append("  WHERE activity = ?;");

        String[] parameters = new String[1];
        parameters[0] = activity;

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            activities.setActivity(result.getString(result.getColumnIndexOrThrow("activity")));

            return activities;
        }

        return null;
    }
}
