package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.TurtleTaggs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TurtleTaggsController {
    private TurtleTaggs turtleTaggs;
    private TurtleController turtleController;
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public TurtleTaggsController(SQLiteDatabase connection){
        this.turtleController = new TurtleController(connection);
        this.connection = connection;
    }

    public void insert(TurtleTaggs turtleTaggs){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idturtle", turtleTaggs.getIdturtle().getIdturtle());
        contentValues.put("dataa", turtleTaggs.getDataa().toString());
        contentValues.put("leftring", turtleTaggs.getLeftring());
        contentValues.put("rightring", turtleTaggs.getRightring());
        contentValues.put("internal_tag", turtleTaggs.getInternal_tag());
        contentValues.put("ccl_measure", turtleTaggs.getCcl_measure());
        contentValues.put("cwl_measure", turtleTaggs.getCwl_measure());

        connection.insertOrThrow("turtletags",null, contentValues);
    }

    public void remove(int idturtle){
        parameters = new String[1];

        parameters[0] = String.valueOf(idturtle);

        connection.delete("turtletags","idturtle = ?",parameters);
    }

    public void edit(TurtleTaggs turtleTaggs){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idturtle", turtleTaggs.getIdturtle().getIdturtle());
        contentValues.put("dataa", turtleTaggs.getDataa().toString());
        contentValues.put("leftring", turtleTaggs.getLeftring());
        contentValues.put("rightring", turtleTaggs.getRightring());
        contentValues.put("internal_tag", turtleTaggs.getInternal_tag());
        contentValues.put("ccl_measure", turtleTaggs.getCcl_measure());
        contentValues.put("cwl_measure", turtleTaggs.getCwl_measure());

        parameters = new String[1];

        parameters[0] = String.valueOf(turtleTaggs.getIdturtle().getIdturtle());

        connection.update("turtletags", contentValues,"idturtle = ?", parameters);
    }

    public List<TurtleTaggs> fetchAll(){
        List<TurtleTaggs> turtleTaggsList = new ArrayList<>();

        sql = new StringBuilder();
        sql.append("SELECT idturtle,");
        sql.append("       dataa,");
        sql.append("       leftring,");
        sql.append("       rightring,");
        sql.append("       internal_tag,");
        sql.append("       ccl_measure,");
        sql.append("       cwl_measure");
        sql.append("  FROM turtletags;");

            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    turtleTaggs = new TurtleTaggs();

                    turtleTaggs.setIdturtle(turtleController.fetchOne(
                            result.getInt(result.getColumnIndexOrThrow("idturtle"))));
                    turtleTaggs.setDataa(
                            new Date(result.getString(result.getColumnIndexOrThrow("dataa"))));
                    turtleTaggs.setLeftring(
                            result.getInt(result.getColumnIndexOrThrow("leftring")));
                    turtleTaggs.setRightring(
                            result.getInt(result.getColumnIndexOrThrow("rightring")));
                    turtleTaggs.setInternal_tag(
                            result.getInt(result.getColumnIndexOrThrow("internal_tag")));
                    turtleTaggs.setCcl_measure(
                            result.getDouble(result.getColumnIndexOrThrow("ccl_measure")));
                    turtleTaggs.setCwl_measure(
                            result.getDouble(result.getColumnIndexOrThrow("cwl_measure")));

                    turtleTaggsList.add(turtleTaggs);
                }while(result.moveToNext());
            }

        return turtleTaggsList;
    }

    public TurtleTaggs fetchOne(int idturtle){
        turtleTaggs = new TurtleTaggs();
        sql = new StringBuilder();

        sql.append("SELECT idturtle,");
        sql.append("       dataa,");
        sql.append("       leftring,");
        sql.append("       rightring,");
        sql.append("       internal_tag,");
        sql.append("       ccl_measure,");
        sql.append("       cwl_measure");
        sql.append("  FROM turtletags");
        sql.append("  WHERE idturtle = ?;");

        parameters = new String[1];
        parameters[0] = String.valueOf(idturtle);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            turtleTaggs = new TurtleTaggs();

            turtleTaggs.setIdturtle(turtleController.fetchOne(
                    result.getInt(result.getColumnIndexOrThrow("idturtle"))));
            turtleTaggs.setDataa(
                    new Date(result.getString(result.getColumnIndexOrThrow("dataa"))));
            turtleTaggs.setLeftring(
                    result.getInt(result.getColumnIndexOrThrow("leftring")));
            turtleTaggs.setRightring(
                    result.getInt(result.getColumnIndexOrThrow("rightring")));
            turtleTaggs.setInternal_tag(
                    result.getInt(result.getColumnIndexOrThrow("internal_tag")));
            turtleTaggs.setCcl_measure(
                    result.getDouble(result.getColumnIndexOrThrow("ccl_measure")));
            turtleTaggs.setCwl_measure(
                    result.getDouble(result.getColumnIndexOrThrow("cwl_measure")));

            return turtleTaggs;
        }

        return null;
    }
}
