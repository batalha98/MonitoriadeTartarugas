package com.example.monitoriadetartarugas.domain.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.monitoriadetartarugas.domain.entitys.Turtle;
import java.util.ArrayList;
import java.util.List;

public class TurtleController {
    private SpecieController specieController;
    private SQLiteDatabase connection;

    public TurtleController(SQLiteDatabase connection){
        this.specieController = new SpecieController(connection);
        this.connection = connection;
    }

    public long insert(Turtle turtle){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idspecie", turtle.getIdspecie().getIdspecie());
        contentValues.put("description", turtle.getDescription());

        return connection.insertOrThrow("turtle",null, contentValues);
    }

    public void remove(int idturtle){
        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idturtle);

        connection.delete("turtle","idturtle = ?",parameters);
    }

    public void edit(Turtle turtle){
        ContentValues contentValues = new ContentValues();

        contentValues.put("idspecie", turtle.getIdspecie().getIdspecie());
        contentValues.put("description", turtle.getDescription());

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(turtle.getIdturtle());

        connection.update("turtle", contentValues,"idturtle = ?", parameters);
    }

    public List<Turtle> fetchAll(){
        List<Turtle> turtleList = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idturtle,");
        sql.append("       idspecie,");
        sql.append("       description");
        sql.append("  FROM turtle;");

        Cursor cursor = connection.rawQuery(sql.toString(), null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            do{
                Turtle turtle = new Turtle();

                turtle.setIdturtle(cursor.getInt(cursor.getColumnIndexOrThrow("idturtle")));
                turtle.setIdspecie(specieController.fetchOne(cursor.getInt(cursor.getColumnIndexOrThrow("idspecie"))));
                turtle.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));

                turtleList.add(turtle);
            }while(cursor.moveToNext());
        }

        return turtleList;
    }

    public Turtle fetchOne(int idturtle){
        Turtle turtle = new Turtle();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT idturtle,");
        sql.append("       idspecie,");
        sql.append("       description");
        sql.append("  FROM turtle");
        sql.append("  WHERE idturtle = ?;");

        String[] parameters = new String[1];
        parameters[0] = String.valueOf(idturtle);

        Cursor cursor = connection.rawQuery(sql.toString(), parameters);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();

            turtle.setIdturtle(cursor.getInt(cursor.getColumnIndexOrThrow("idturtle")));
            turtle.setIdspecie(specieController.fetchOne(cursor.getInt(cursor.getColumnIndexOrThrow("idspecie"))));
            turtle.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));

            return turtle;
        }

        return null;
    }
}
