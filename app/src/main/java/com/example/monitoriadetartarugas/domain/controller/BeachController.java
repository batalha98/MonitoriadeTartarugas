package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.Beach;

import java.util.ArrayList;
import java.util.List;

public class BeachController {
    private SQLiteDatabase connection;

    public BeachController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Beach beach){
        ContentValues contentValues = new ContentValues();

        contentValues.put("beach", beach.getBeach());
        contentValues.put("island", beach.getIsland());

        connection.insertOrThrow("beach",null, contentValues);
    }

    public void remove(String beach){
        String[] parameters = new String[1];
        parameters[0] = beach;

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
        sql.append("SELECT beach, island");
        sql.append("  FROM beach");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    Beach beach = new Beach();

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

    public Beach fetchOne(String beach){
        Beach beach1 = new Beach();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT beach,");
        sql.append("       island");
        sql.append("  FROM beach");
        sql.append("  WHERE beach = ?;");

        String[] parameters = new String[1];
        parameters[0] = beach;

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            beach1.setBeach(result.getString(result.getColumnIndexOrThrow("beach")));
            beach1.setIsland(result.getString(result.getColumnIndexOrThrow("island")));

            return beach1;
        }
        return null;
    }

}
