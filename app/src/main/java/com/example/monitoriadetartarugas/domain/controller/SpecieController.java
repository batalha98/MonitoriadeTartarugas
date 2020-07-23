package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;

import com.example.monitoriadetartarugas.R;
import com.example.monitoriadetartarugas.domain.entitys.Specie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpecieController {
    private SQLiteDatabase connection;
    private String[] parameters;
    private StringBuilder sql;

    public SpecieController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Specie specie){
        ContentValues contentValues = new ContentValues();

        contentValues.put("specie", specie.getSpecie());

        connection.insertOrThrow("specie",null, contentValues);
    }

    public void remove(int idturtle){
        parameters = new String[1];
        parameters[0] = String.valueOf(idturtle);

        connection.delete("specie","idspecie = ?",parameters);
    }

    public void edit(String new_specie, Specie specie){
        ContentValues contentValues = new ContentValues();

        contentValues.put("specie",new_specie);

        parameters = new String[1];
        parameters[0] = String.valueOf(specie.getIdspecie());

        connection.update("specie", contentValues,"idspecie = ?", parameters);
    }

    public List<Specie> fetchAll(){
        List<Specie> specieList = new ArrayList<>();

        sql = new StringBuilder();
        sql.append("SELECT idspecie,");
        sql.append("       specie");
        sql.append("  FROM specie");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    Specie specie = new Specie();

                    specie.setIdspecie(result.getInt(result.getColumnIndexOrThrow("idspecie")));
                    specie.setSpecie(result.getString(result.getColumnIndexOrThrow("specie")));

                    specieList.add(specie);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return specieList;
    }

    public Specie fetchOne(int idspecie){
        Specie specie = new Specie();

        sql = new StringBuilder();
        sql.append("SELECT idspecie,");
        sql.append("       specie");
        sql.append("  FROM specie");
        sql.append("  WHERE idspecie = ?;");

        parameters = new String[1];
        parameters[0] = String.valueOf(idspecie);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            specie.setIdspecie(result.getInt(result.getColumnIndexOrThrow("idspecie")));
            specie.setSpecie(result.getString(result.getColumnIndexOrThrow("specie")));

            return specie;
        }

        return null;
    }

    public Specie fetchOne(String specie_name){
        Specie specie = new Specie();

        sql = new StringBuilder();
        sql.append("SELECT idspecie,");
        sql.append("       specie");
        sql.append("  FROM specie");
        sql.append("  WHERE specie = ?;");

        parameters = new String[1];
        parameters[0] = specie_name;

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            specie.setIdspecie(result.getInt(result.getColumnIndexOrThrow("idspecie")));
            specie.setSpecie(result.getString(result.getColumnIndexOrThrow("specie")));

            return specie;
        }

        return null;
    }

}
