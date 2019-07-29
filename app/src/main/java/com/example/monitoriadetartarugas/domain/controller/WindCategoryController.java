package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.monitoriadetartarugas.domain.entitys.WindCategory;

import java.util.ArrayList;
import java.util.List;

public class WindCategoryController {
    private SQLiteDatabase connection;

    public WindCategoryController(SQLiteDatabase connection){
        this.connection = connection;
    }

    public void insert(WindCategory windCategory){
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", windCategory.getName());

        connection.insertOrThrow("windcategory",null, contentValues);
    }

    public void remove(int idwc){
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idwc);

        connection.delete("windcategory","idwc = ?",parameters);
    }

    public void edit(WindCategory windCategory){
        ContentValues contentValues = new ContentValues();

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(windCategory.getIdwc());

        connection.update("windcategory", contentValues,"idwc = ?", parameters);
    }

    public List<WindCategory> fetchAll(){
        List<WindCategory> windCategoryList = new ArrayList<>();
        WindCategory windCategory;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idwc,");
        sql.append("       name");
        sql.append("  FROM windcategory;");

        try {
            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0){
                result.moveToFirst();

                do{
                    windCategory = new WindCategory();

                    windCategory.setIdwc(result.getInt(result.getColumnIndexOrThrow("idwc")));
                    windCategory.setName(result.getString(result.getColumnIndexOrThrow("name")));

                    windCategoryList.add(windCategory);
                }while(result.moveToNext());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return windCategoryList;
    }

    public WindCategory fetchOne(int idwc){
        WindCategory windCategory = new WindCategory();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idwc,");
        sql.append("       name");
        sql.append("  FROM windcategory;");
        sql.append("  WHERE idwc = ?");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idwc);

        Cursor result = connection.rawQuery(sql.toString(), null);

        if(result.getCount() > 0){
            result.moveToFirst();

            windCategory.setIdwc(result.getInt(result.getColumnIndexOrThrow("idwc")));
            windCategory.setName(result.getString(result.getColumnIndexOrThrow("name")));

            return windCategory;
        }

        return null;
    }

}
