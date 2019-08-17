package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.NestWithoutTurtle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NestWithoutTurtleController {
    private NestWithoutTurtle nestWithoutTurtle;
    private SpecieController specieController;
    private NestController nestController;

    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public NestWithoutTurtleController(SQLiteDatabase connection){
        this.specieController = new SpecieController(connection);
        this.nestController = new NestController(connection);
        this.connection = connection;
    }

    public void insert(NestWithoutTurtle nestWithoutTurtle){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", nestWithoutTurtle.getIdnest().getIdnest());
        contentValues.put("idspecie", nestWithoutTurtle.getIdspecie().getIdspecie());

        connection.insertOrThrow("nestwithoutturtle",null, contentValues);
    }

    public void remove(int idnest){
        parameters = new String[1];

        parameters[0] = String.valueOf(idnest);

        connection.delete("nestwithoutturtle","idnest = ?",parameters);
    }

    public void edit(NestWithoutTurtle nestWithoutTurtle){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idnest", nestWithoutTurtle.getIdnest().getIdnest());
        contentValues.put("idspecie", nestWithoutTurtle.getIdspecie().getIdspecie());

        parameters = new String[1];
        parameters[0] = String.valueOf(nestWithoutTurtle.getIdnest().getIdnest());

        connection.update("nestwithoutturtle", contentValues,"idnest = ?", parameters);
    }

    public List<NestWithoutTurtle> fetchAll(){
        List<NestWithoutTurtle> nestWithoutTurtleList = new ArrayList<>();
        sql = new StringBuilder();

        sql.append("SELECT idnest,");
        sql.append("       idspecie");
        sql.append("  FROM nestwithoutturtle;");

        Cursor result = connection.rawQuery(sql.toString(), null);

        if(result.getCount() > 0){
            result.moveToFirst();

            do{
                nestWithoutTurtle = new NestWithoutTurtle();

                nestWithoutTurtle.setIdnest(nestController.fetchOne(
                        result.getInt(result.getColumnIndexOrThrow("idnest"))));
                nestWithoutTurtle.setIdspecie(specieController.fetchOne(
                        result.getInt(result.getColumnIndexOrThrow("idspecie"))));

                nestWithoutTurtleList.add(nestWithoutTurtle);
            }while(result.moveToNext());
        }

        return nestWithoutTurtleList;
    }

    public NestWithoutTurtle fetchOne(int idnest){
        nestWithoutTurtle = new NestWithoutTurtle();
        sql = new StringBuilder();

        sql.append("SELECT idnest,");
        sql.append("       idspecie");
        sql.append("  FROM nestwithoutturtle");
        sql.append("  WHERE idnest = ?;");

        parameters = new String[1];
        parameters[0] = String.valueOf(idnest);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            nestWithoutTurtle.setIdnest(nestController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idnest"))));
            nestWithoutTurtle.setIdspecie(specieController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idspecie"))));

            return nestWithoutTurtle;
        }

        return null;
    }
}
