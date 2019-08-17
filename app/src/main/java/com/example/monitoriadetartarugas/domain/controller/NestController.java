package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Nest;
import com.example.monitoriadetartarugas.domain.entitys.Observation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NestController {
    private Nest nest;
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public NestController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public long insert(Nest nest){
        ContentValues contentValues = new ContentValues();

        contentValues.put("depth", nest.getDepth());
        contentValues.put("eggs_quantity", nest.getEggs_quantity());
        contentValues.put("distance", nest.getDistance());
        contentValues.put("width", nest.getWidth());
        contentValues.put("description", nest.getDescription());

        return connection.insertOrThrow("nest",null, contentValues);
    }

    public void remove(int idnest){
        parameters = new String[1];

        parameters[0] = String.valueOf(idnest);

        connection.delete("nest","idnest = ?",parameters);
    }

    public void edit(Nest nest){
        ContentValues contentValues = new ContentValues();

        contentValues.put("depth", nest.getDepth());
        contentValues.put("eggs_qunatity", nest.getEggs_quantity());
        contentValues.put("distance", nest.getDistance());
        contentValues.put("width", nest.getWidth());
        contentValues.put("description", nest.getDescription());

        parameters = new String[1];
        parameters[0] = String.valueOf(nest.getIdnest());

        connection.update("nest", contentValues,"idnest = ?", parameters);
    }

    public List<Nest> fetchAll(){
        List<Nest> nestList = new ArrayList<>();

        sql = new StringBuilder();
        sql.append("SELECT idnest,");
        sql.append("       depth,");
        sql.append("       eggs_quantity,");
        sql.append("       distance,");
        sql.append("       width,");
        sql.append("       description");
        sql.append("  FROM nest;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    nest = new Nest();

                    nest.setIdnest(result.getInt(result.getColumnIndexOrThrow("idnest")));
                    nest.setDepth(result.getInt(result.getColumnIndexOrThrow("depth")));
                    nest.setEggs_quantity(result.getInt(result.getColumnIndexOrThrow("eggs_quantity")));
                    nest.setDistance(result.getFloat(result.getColumnIndexOrThrow("distance")));
                    nest.setWidth(result.getFloat(result.getColumnIndexOrThrow("width")));
                    nest.setDescription(result.getString(result.getColumnIndexOrThrow("description")));

                    nestList.add(nest);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return nestList;
    }

    public Nest fetchOne(int idnest){
        nest = new Nest();

        sql = new StringBuilder();
        sql.append("SELECT idnest,");
        sql.append("       depth,");
        sql.append("       eggs_quantity,");
        sql.append("       distance,");
        sql.append("       width,");
        sql.append("       description");
        sql.append("  FROM nest");
        sql.append("  WHERE idnest = ?;");

        parameters = new String[1];
        parameters[0] = String.valueOf(idnest);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            nest.setIdnest(result.getInt(result.getColumnIndexOrThrow("idnest")));
            nest.setDepth(result.getInt(result.getColumnIndexOrThrow("depth")));
            nest.setEggs_quantity(result.getInt(result.getColumnIndexOrThrow("eggs_quantity")));
            nest.setDistance(result.getFloat(result.getColumnIndexOrThrow("distance")));
            nest.setWidth(result.getFloat(result.getColumnIndexOrThrow("width")));
            nest.setDescription(result.getString(result.getColumnIndexOrThrow("description")));

            return nest;
        }

        return null;
    }
}
