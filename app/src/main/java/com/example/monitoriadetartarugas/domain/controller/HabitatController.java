package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Habitat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HabitatController {
    private Habitat habitat;
    private SQLiteDatabase connection;

    public HabitatController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Habitat habitat){
        ContentValues contentValues = new ContentValues();

        contentValues.put("habitat", habitat.getHabitat());

        connection.insertOrThrow("habitat",null, contentValues);
    }

    public void remove(int idhabitat){
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idhabitat);

        connection.delete("habitat","idhabitat = ?",parameters);
    }

    public void edit(Habitat habitat){
        ContentValues contentValues = new ContentValues();

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(habitat.getIdhabitat());

        connection.update("habitat", contentValues,"idhabitat = ?", parameters);
    }

    public List<Habitat> fetchAll(){
        List<Habitat> habitatList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idhabitat,");
        sql.append("       habitat");
        sql.append("  FROM habitat;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    Habitat habitat = new Habitat();

                    habitat.setIdhabitat(result.getInt(result.getColumnIndexOrThrow("idhabitat")));
                    habitat.setHabitat(result.getString(result.getColumnIndexOrThrow("habitat")));

                    habitatList.add(habitat);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return habitatList;
    }

    public Habitat fetchOne(int idhabitat){
        Habitat habitat = new Habitat();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idhabitat,");
        sql.append("       habitat");
        sql.append("  FROM habitat");
        sql.append("  WHERE idhabitat = ?;");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idhabitat);

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            habitat.setIdhabitat(result.getInt(result.getColumnIndexOrThrow("idhabitat")));
            habitat.setHabitat(result.getString(result.getColumnIndexOrThrow("habitat")));

            return habitat;
        }

        return null;
    }
}
