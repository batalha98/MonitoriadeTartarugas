package com.example.monitoriadetartarugas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataOpenHelper extends SQLiteOpenHelper {

    public DataOpenHelper(Context context) {
        super(context, "turtlesDB",null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDDL.createTableActivities());
        db.execSQL("INSERT INTO activities (idactivity, activity) VALUES (1, 'making a nest')");
        db.execSQL("INSERT INTO activities (idactivity, activity) VALUES (2, 'running to the beach')");
        db.execSQL("INSERT INTO activities (idactivity, activity) VALUES (3, 'caught with fisherman')");

        db.execSQL(ScriptDDL.createTableIsland());
        db.execSQL("INSERT INTO island (island) VALUES ('vamizi')");
        db.execSQL("INSERT INTO island (island) VALUES ('ilha de mocambique')");
        db.execSQL("INSERT INTO island (island) VALUES ('ibo')");

        db.execSQL(ScriptDDL.createTableBeach());
        db.execSQL("INSERT INTO beach (beach,island) VALUES ('wimbi','vamizi')");
        db.execSQL("INSERT INTO beach (beach,island) VALUES ('maringanha','vamizi')");
        db.execSQL("INSERT INTO beach (beach,island) VALUES ('chuiba','ibo')");
//        db.execSQL(ScriptDDL.createTableHabitat());
//        db.execSQL(ScriptDDL.createTableNest());

        db.execSQL(ScriptDDL.createTableSpecie());
        db.execSQL("INSERT INTO specie (idspecie, specie) VALUES (1, 'chelonya mydas')");
        db.execSQL("INSERT INTO specie (idspecie, specie) VALUES (2, 'caretta caretta')");
        db.execSQL("INSERT INTO specie (idspecie, specie) VALUES (3, 'dermochelys coriacea')");
        db.execSQL("INSERT INTO specie (idspecie, specie) VALUES (4, 'eretmochelys imbricata')");
        db.execSQL("INSERT INTO specie (idspecie, specie) VALUES (5, 'lepidochelys olivacea')");

        db.execSQL(ScriptDDL.createTableTurtle());
        db.execSQL(ScriptDDL.createTableWindCategory());
        db.execSQL("INSERT INTO windcategory (name) VALUES ('strong')");
        db.execSQL("INSERT INTO windcategory (name) VALUES ('very strong')");
////
        db.execSQL(ScriptDDL.createTableWindDirection());
        db.execSQL("INSERT INTO winddirection (name) VALUES ('south')");
        db.execSQL("INSERT INTO winddirection (name) VALUES ('north')");

//        db.execSQL(ScriptDDL.createTableObserver());
        db.execSQL(ScriptDDL.createTableObservation());
//        db.execSQL(ScriptDDL.createTableTurtleTaggs());
//        db.execSQL(ScriptDDL.createTableTurtleNest());
//        db.execSQL(ScriptDDL.createTableNestLocalization());
//        db.execSQL(ScriptDDL.createTableHatchlings());
//        db.execSQL(ScriptDDL.createTableLocalizationAndObservation());
//        db.execSQL(ScriptDDL.createTableNestWithouTurtle());
//        db.execSQL(ScriptDDL.createTableObservationAndObserver());
//        db.execSQL(ScriptDDL.createTableObserverObservation());
        db.execSQL(ScriptDDL.createTableTurtleActivities());
//        db.execSQL(ScriptDDL.createTableUsers());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
