package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.WindDirection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WindDirectionController {
    private SQLiteDatabase connection;

    public WindDirectionController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(WindDirection windDirection){
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", windDirection.getName());

        connection.insertOrThrow("winddirection",null, contentValues);
    }

    public void remove(int idwc){
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idwc);

        connection.delete("windDirection","idwd = ?",parameters);
    }

    public void edit(String new_wd, WindDirection windDirection){
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", new_wd);

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(windDirection.getIdwd());

        connection.update("windDirection", contentValues,"idwd = ?", parameters);
    }

    public List<WindDirection> fetchAll(){
        List<WindDirection> windDirectionList = new ArrayList<>();
        WindDirection windDirection;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idwd,");
        sql.append("       name");
        sql.append("  FROM windDirection;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    windDirection = new WindDirection();

                    windDirection.setIdwd(result.getInt(result.getColumnIndexOrThrow("idwd")));
                    windDirection.setName(result.getString(result.getColumnIndexOrThrow("name")));

                    windDirectionList.add(windDirection);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return windDirectionList;
    }

    public WindDirection fetchOne(int idwd){
        WindDirection windDirection = new WindDirection();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idwd,");
        sql.append("       name");
        sql.append("  FROM winddirection");
        sql.append("  WHERE idwd = ?");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idwd);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToLast();

            windDirection.setIdwd(result.getInt(result.getColumnIndexOrThrow("idwd")));
            windDirection.setName(result.getString(result.getColumnIndexOrThrow("name")));

            return windDirection;
        }

        return null;
    }

    public WindDirection fetchOne(String wd){
        WindDirection windDirection = new WindDirection();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idwd,");
        sql.append("       name");
        sql.append("  FROM winddirection");
        sql.append("  WHERE name = ?");

        String[] parameters = new String[1];
        parameters[0] = wd;

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToLast();

            windDirection.setIdwd(result.getInt(result.getColumnIndexOrThrow("idwd")));
            windDirection.setName(result.getString(result.getColumnIndexOrThrow("name")));

            return windDirection;
        }

        return null;
    }
}
