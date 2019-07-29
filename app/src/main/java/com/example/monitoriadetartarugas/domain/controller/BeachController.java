package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Beach;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BeachController {
    private Beach beach;
    private SQLiteDatabase connection;

    public BeachController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Beach beach){
        ContentValues contentValues = new ContentValues();

        contentValues.put("beach_name", beach.getBeach());
        contentValues.put("island_name", beach.getIsland());

        connection.insertOrThrow("beach",null, contentValues);
    }

    public void remove(int idwc){
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idwc);

        connection.delete("beach","beach = ?",parameters);
    }

    public void edit(Beach beach){
        ContentValues contentValues = new ContentValues();

        String[] parameters = new String[1];
        parameters[0] = beach.getBeach();

        connection.update("beach", contentValues,"beach = ?", parameters);
    }

    public List<Beach> fetchAll(){
        List<Beach> beachList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT beach,");
        sql.append("       island");
        sql.append("  FROM beach;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    beach = new Beach();

                    beach.setBeach(result.getString(result.getColumnIndexOrThrow("beach")));
                    beach.setIsland(result.getString(result.getColumnIndexOrThrow("island")));

                    beachList.add(beach);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return beachList;
    }

    public Beach fetchOne(int idwc){
        beach = new Beach();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT beach,");
        sql.append("       island");
        sql.append("  FROM beach");
        sql.append("  WHERE beach = ?;");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idwc);

        Cursor result = connection.rawQuery(sql.toString(), null);

        if(result.getCount() > 0){
            result.moveToFirst();

            beach.setBeach(result.getString(result.getColumnIndexOrThrow("beach")));
            beach.setIsland(result.getString(result.getColumnIndexOrThrow("island")));

            return beach;
        }

        return null;
    }



}
