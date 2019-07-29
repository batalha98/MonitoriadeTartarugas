package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Specie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpecieController {

    private SQLiteDatabase connection;

    public SpecieController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Specie specie){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idspecie", specie.idspecie);

        connection.insertOrThrow("specie",null, contentValues);
    }

    public void remove(int idturtle){
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idturtle);

        connection.delete("specie","idspecie = ?",parameters);
    }

    public void edit(Specie specie){
        ContentValues contentValues = new ContentValues();

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(specie.idspecie);

        connection.update("specie", contentValues,"idspecie = ?", parameters);
    }

    public List<Specie> fetchAll(){
        List<Specie> specieList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idspecie, specie");
        sql.append("    FROM specie");

        Cursor cursor = connection.rawQuery(sql.toString(), null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Specie specie = new Specie();

                specie.idspecie = cursor.getInt(cursor.getColumnIndexOrThrow("idspecie"));
                specie.specie = cursor.getString(cursor.getColumnIndexOrThrow("specie"));

                specieList.add(specie);
            }while(cursor.moveToNext());
        }

        return specieList;
    }

    public Specie fetchOne(int idturtle){
        Specie specie = new Specie();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idspecie, specie");
        sql.append("    FROM specie");
        sql.append("    WHERE idspecie = ?");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idturtle);

        Cursor cursor = connection.rawQuery(sql.toString(), null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            specie.idspecie = cursor.getInt(cursor.getColumnIndexOrThrow("idspecie"));
            specie.specie = cursor.getString(cursor.getColumnIndexOrThrow("specie"));

            return specie;
        }

        return null;
    }

}
