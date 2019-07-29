package com.example.monitoriadetartarugas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataOpenHelper extends SQLiteOpenHelper {

    public DataOpenHelper(Context context) {
        super(context, "turtlesDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(ScriptDDL.createTableActivities());
//        db.execSQL(ScriptDDL.createTableIsland());
//        db.execSQL(ScriptDDL.createTableBeach());
//        db.execSQL(ScriptDDL.createTableHabitat());
//        db.execSQL(ScriptDDL.createTableNest());
        db.execSQL(ScriptDDL.createTableSpecie());
        db.execSQL(ScriptDDL.createTableTurtle());
//        db.execSQL(ScriptDDL.createTableWindCategory());
//        db.execSQL(ScriptDDL.createTableWindDirection());
//        db.execSQL(ScriptDDL.createTableObserver());
//        db.execSQL(ScriptDDL.createTableObservation());
//        db.execSQL(ScriptDDL.createTableTurtleTaggs());
//        db.execSQL(ScriptDDL.createTableTurtleNest());
//        db.execSQL(ScriptDDL.createTableNestLocalization());
//        db.execSQL(ScriptDDL.createTableHatchlings());
//        db.execSQL(ScriptDDL.createTableLocalizationAndObservation());
//        db.execSQL(ScriptDDL.createTableNestWithouTurtle());
//        db.execSQL(ScriptDDL.createTableObservationAndObserver());
//        db.execSQL(ScriptDDL.createTableObserverObservation());
//        db.execSQL(ScriptDDL.createTableTurtleActivities());
//        db.execSQL(ScriptDDL.createTableUsers());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
