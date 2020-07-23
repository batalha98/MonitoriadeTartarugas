package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.monitoriadetartarugas.domain.entitys.Island;

import java.util.ArrayList;
import java.util.List;

public class IslandController {
    private Island island1;
    private SQLiteDatabase connection;

    public IslandController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(Island island){
        ContentValues contentValues = new ContentValues();

        contentValues.put("island", island.getIsland());

        connection.insertOrThrow("island",null, contentValues);
    }

    public void remove(String island){
        String[] parameters = new String[1];
        parameters[0] = island;

        connection.delete("island","island = ?",parameters);
    }

    public void edit(String old_island, String new_island){
        ContentValues contentValues = new ContentValues();

        contentValues.put("island",new_island);

        String[] parameters = new String[1];
        parameters[0] = old_island;

        connection.update("island", contentValues,"island = ?", parameters);
    }

    public List<Island> fetchAll(){
        List<Island> islandList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT island");
        sql.append("  FROM island;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    island1 = new Island();

                    island1.setIsland(result.getString(result.getColumnIndexOrThrow("island")));

                    islandList.add(island1);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return islandList;
    }

    public Island fetchOne(String island){
        island1 = new Island();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT island");
        sql.append("  FROM island");
        sql.append("  WHERE island = ?;");

        String[] parameters = new String[1];
        parameters[0] = island;

        Cursor result = connection.rawQuery(sql.toString(), parameters);

        if(result.getCount() > 0){
            result.moveToFirst();

            island1.setIsland(result.getString(result.getColumnIndexOrThrow("island")));

            return island1;
        }

        return null;
    }
}
