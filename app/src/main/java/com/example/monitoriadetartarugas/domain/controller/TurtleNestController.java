package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Observation;
import com.example.monitoriadetartarugas.domain.entitys.TurtleNest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurtleNestController {
    private TurtleNest turtleNest;
    private TurtleController turtleController;
    private NestController nestController;
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public TurtleNestController(SQLiteDatabase connection){
        this.turtleController = new TurtleController(connection);
        this.nestController = new NestController(connection);
        this.connection = connection;
    }

    public void insert(TurtleNest turtleNest){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", turtleNest.getIdnest().getIdnest());
        contentValues.put("idturtle", turtleNest.getIdturtle().getIdturtle());

        connection.insertOrThrow("turtlenest",null, contentValues);
    }

    public void remove(int idnest){
        parameters = new String[1];

        parameters[0] = String.valueOf(idnest);

        connection.delete("turtlenest","idnest = ?",parameters);
    }

    public void edit(TurtleNest turtleNest){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", turtleNest.getIdnest().getIdnest());
        contentValues.put("idturtle", turtleNest.getIdturtle().getIdturtle());

        parameters = new String[1];

        parameters[0] = String.valueOf(turtleNest.getIdnest().getIdnest());

        connection.update("turtlenest", contentValues,"idnest = ?", parameters);
    }

    public List<TurtleNest> fetchAll(){
        List<TurtleNest> turtleNestList = new ArrayList<>();

        sql = new StringBuilder();
        sql.append("SELECT idnest,");
        sql.append("       idturtle");
        sql.append("  FROM turtlenest;");

        Cursor result = connection.rawQuery(sql.toString(), null);

        if(result.getCount() > 0){
            result.moveToFirst();

            do{
                turtleNest = new TurtleNest();

                turtleNest.setIdnest(nestController.fetchOne(
                        result.getInt(result.getColumnIndexOrThrow("idnest"))));
                turtleNest.setIdturtle(turtleController.fetchOne(
                        result.getInt(result.getColumnIndexOrThrow("idturtle"))));

                turtleNestList.add(turtleNest);
            }while(result.moveToNext());
        }

        return turtleNestList;
    }

    public TurtleNest fetchOne(int idnest){
        turtleNest = new TurtleNest();

        sql = new StringBuilder();
        sql.append("SELECT idnest,");
        sql.append("       idturtle");
        sql.append("  FROM turtlenest");
        sql.append("  WHERE idnest = ?;");

        parameters = new String[1];
        parameters[0] = String.valueOf(idnest);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            turtleNest.setIdnest(nestController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idnest"))));
            turtleNest.setIdturtle(turtleController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idturtle"))));

            return turtleNest;
        }

        return null;
    }
}
