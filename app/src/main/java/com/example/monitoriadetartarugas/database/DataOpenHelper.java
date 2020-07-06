package com.example.monitoriadetartarugas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Adapter;

public class DataOpenHelper extends SQLiteOpenHelper {
    public static String DB_NAME = "turtlesDB";

    public DataOpenHelper(Context context) {
        super(context, DB_NAME,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptDDL.createTableSpecie());
        db.execSQL("INSERT INTO specie (specie) VALUES ('Chelonya Mydas')");
        db.execSQL("INSERT INTO specie (specie) VALUES ('Caretta Caretta')");
        db.execSQL("INSERT INTO specie (specie) VALUES ('Dermochelys Coriacea')");

        db.execSQL(ScriptDDL.createTableTurtle());

        db.execSQL(ScriptDDL.createTableIsland());
        db.execSQL("INSERT INTO island (island) VALUES ('Vamizi')");
        db.execSQL("INSERT INTO island (island) VALUES ('Ilha de Mocambique')");
        db.execSQL("INSERT INTO island (island) VALUES ('Ibo')");

        db.execSQL(ScriptDDL.createTableBeach());
        db.execSQL("INSERT INTO beach (beach,island) VALUES ('Wimbi','Vamizi')");
        db.execSQL("INSERT INTO beach (beach,island) VALUES ('Maringanha','Vamizi')");
        db.execSQL("INSERT INTO beach (beach,island) VALUES ('Chuiba','Ibo')");

        db.execSQL(ScriptDDL.createTableActivities());
        db.execSQL("INSERT INTO activities (idactivity, activity) VALUES (1, 'Making a nest')");
        db.execSQL("INSERT INTO activities (idactivity, activity) VALUES (2, 'Running to the beach')");
        db.execSQL("INSERT INTO activities (idactivity, activity) VALUES (3, 'Caught with fisherman')");

        db.execSQL(ScriptDDL.createTableWindCategory());
        db.execSQL("INSERT INTO windcategory (name) VALUES ('Strong')");
        db.execSQL("INSERT INTO windcategory (name) VALUES ('Very strong')");

        db.execSQL(ScriptDDL.createTableWindDirection());
        db.execSQL("INSERT INTO winddirection (name) VALUES ('South')");
        db.execSQL("INSERT INTO winddirection (name) VALUES ('North')");
//        db.execSQL(ScriptDDL.createTableTurtleActivities());

        db.execSQL(ScriptDDL.createTableObservation());

        db.execSQL(ScriptDDL.createTableNest());
        db.execSQL(ScriptDDL.createTableHabitat());
        db.execSQL("INSERT INTO habitat (idhabitat,habitat) VALUES (1,'In the bush')");
        db.execSQL("INSERT INTO habitat (idhabitat,habitat) VALUES (2,'Near the margin')");
        db.execSQL("INSERT INTO habitat (idhabitat,habitat) VALUES (3,'Near rocks')");

        db.execSQL(ScriptDDL.createTableNestLocalization());

//        db.execSQL(ScriptDDL.createTableLocalizationAndObservation());
//        db.execSQL(ScriptDDL.createTableObserver());

        db.execSQL(ScriptDDL.createTableHatchlings());
        db.execSQL(ScriptDDL.createTableTurtleTaggs());
        db.execSQL(ScriptDDL.createTableTurtleNest());
        db.execSQL(ScriptDDL.createTableNestWithouTurtle());
//        db.execSQL(ScriptDDL.createTableObservationAndObserver());
//        db.execSQL(ScriptDDL.createTableObserverObservation());
//        db.execSQL(ScriptDDL.createTableUsers());
//        db.execSQL("INSERT INTO users (email,password) VALUES ('test@gmail.com','123')");

        db.execSQL(ScriptDDL.createTableTurtleNestData());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ScriptDDL.dropTableSpecie());
        db.execSQL(ScriptDDL.dropTableBeach());
        db.execSQL(ScriptDDL.dropTableActivities());
        db.execSQL(ScriptDDL.dropTableHabitat());
        db.execSQL(ScriptDDL.dropTableHatchlings());
        db.execSQL(ScriptDDL.dropTableIsland());
        db.execSQL(ScriptDDL.dropTableLocalizationandobservation());
        db.execSQL(ScriptDDL.dropTableNest());
        db.execSQL(ScriptDDL.dropTableNestlocalization());
        db.execSQL(ScriptDDL.dropTableNestwithoutturtle());
        db.execSQL(ScriptDDL.dropTableObservation());
        db.execSQL(ScriptDDL.dropTableObservationAndObserver());
        db.execSQL(ScriptDDL.dropTableTurtle());
        db.execSQL(ScriptDDL.dropTableTurtleactivities());
        db.execSQL(ScriptDDL.dropTableTurtlenest());
        db.execSQL(ScriptDDL.dropTableTurtletags());
        db.execSQL(ScriptDDL.dropTableWC());
        db.execSQL(ScriptDDL.dropTableWD());
        db.execSQL(ScriptDDL.dropTableUsers());
        db.execSQL(ScriptDDL.dropTableObserver());
        db.execSQL(ScriptDDL.dropTableObserverobservation());

        onCreate(db);
    }
}
