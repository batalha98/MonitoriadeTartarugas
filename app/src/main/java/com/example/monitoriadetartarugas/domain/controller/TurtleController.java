package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Turtle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TurtleController {

    private SQLiteDatabase connection;

    public TurtleController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Turtle turtle){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idspecie", turtle.idspecie);

        connection.insertOrThrow("turtle",null, contentValues);
    }

    public void remove(int idturtle){
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idturtle);

        connection.delete("turtle","idturtle = ?",parameters);
    }

    public void edit(Turtle turtle){
        ContentValues contentValues = new ContentValues();

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(turtle.idspecie);

        connection.update("turtle", contentValues,"idturtle = ?", parameters);
    }

    public List<Turtle> fetchAll(){
        List<Turtle> turtleList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idturtle, idspecie");
        sql.append("    FROM turtle");

        Cursor cursor = connection.rawQuery(sql.toString(), null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Turtle turtle = new Turtle();

                turtle.idturtle = cursor.getInt(cursor.getColumnIndexOrThrow("idturtle"));
                turtle.idspecie = cursor.getInt(cursor.getColumnIndexOrThrow("idspecie"));

                turtleList.add(turtle);
            }while(cursor.moveToNext());
        }

        return turtleList;
    }

    public Turtle fetchOne(int idturtle){
        Turtle turtle = new Turtle();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idturtle, idspecie");
        sql.append("    FROM turtle");
        sql.append("    WHERE idturtle = ?");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idturtle);

        Cursor cursor = connection.rawQuery(sql.toString(), null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            turtle.idturtle = cursor.getInt(cursor.getColumnIndexOrThrow("idturtle"));
            turtle.idspecie = cursor.getInt(cursor.getColumnIndexOrThrow("idspecie"));

            return turtle;
        }

        return null;
    }
}
