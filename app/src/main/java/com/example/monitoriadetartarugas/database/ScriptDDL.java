package com.example.monitoriadetartarugas.database;

public class ScriptDDL {

    public static String createTableActivities(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE activities (");
        builder.append("    idactivity INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        builder.append("    activity   VARCHAR (45) DEFAULT NULL)");

        return builder.toString();
    }

    public static String createTableBeach(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE beach (");
        builder.append("    beach  VARCHAR (45) NOT NULL,");
        builder.append("    island VARCHAR (45) DEFAULT NULL,");
        builder.append("    PRIMARY KEY (");
        builder.append("        beach),");

        builder.append("    FOREIGN KEY (");
        builder.append("        island)");

        builder.append("    REFERENCES island (island) ON DELETE RESTRICT ON UPDATE RESTRICT)");

        return builder.toString();
    }

    public static String createTableTurtle(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE turtle (");
        builder.append("idturtle INTEGER  NOT NULL ");
        builder.append("PRIMARY KEY AUTOINCREMENT,");
        builder.append("idspecie INT (11) DEFAULT NULL,");
        builder.append("FOREIGN KEY (");
        builder.append("idspecie)");
        builder.append("REFERENCES specie (idspecie) ON DELETE CASCADE\n" +
                "                ON UPDATE CASCADE");

        return builder.toString();
    }

    public static String createTableSpecie(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE specie (");
        builder.append("    idspecie INTEGER NOT NULL " +
                "PRIMARY KEY AUTOINCREMENT,");
        builder.append("    specie   VARCHAR (45) DEFAULT NULL)");

        return builder.toString();
    }

    public static String createTableNest(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE nest (");
                builder.append("idnest INTEGER       NOT NULL " +
                        "PRIMARY KEY AUTOINCREMENT,");
                builder.append("depth         INT (11)      DEFAULT NULL,");
                builder.append("eggs_quantity INT (11)      DEFAULT NULL,");
                builder.append("distance      FLOAT         DEFAULT NULL,");
                builder.append("width         FLOAT         DEFAULT NULL,");
                builder.append(" description   VARCHAR (255) DEFAULT NULL)");

        return builder.toString();
    }

    public static String createTableWindCategory(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE windcategory (");
                builder.append("idwc INTEGER      NOT NULL " +
                        "PRIMARY KEY AUTOINCREMENT,");
                builder.append("name VARCHAR (45) DEFAULT NULL)");

        return builder.toString();
    }

    public static String createTableWindDirection(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE winddirection (");
                builder.append("idwd INTEGER      NOT NULL " +
                        "PRIMARY KEY AUTOINCREMENT,");
                builder.append("name VARCHAR (45) DEFAULT NULL)");

        return builder.toString();
    }

    public static String createTableIsland(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE island (");
                builder.append("island VARCHAR (45) NOT NULL,");
                builder.append("PRIMARY KEY (");
                        builder.append("island))");

        return builder.toString();
    }

    public static String createTableUsers(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE users (");
                builder.append("email    VARCHAR (255) NOT NULL,");
        builder.append("password VARCHAR (255) DEFAULT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append("email))");

        return builder.toString();
    }

    public static String createTableObservation(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE observation (");
                builder.append("idturtle     INT (11)     NOT NULL,");
        builder.append("beach        VARCHAR (45) NOT NULL,");
        builder.append("dataa        DATETIME     NOT NULL,");
        builder.append("wc           INT (11)     NOT NULL,");
        builder.append("wd           INT (11)     DEFAULT NULL,");
        builder.append("beach_height FLOAT        DEFAULT NULL,");
        builder.append("beach_time   DATETIME     DEFAULT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append("idturtle,");
                builder.append("beach,");
                builder.append("dataa),");
        builder.append(" FOREIGN KEY (");
        builder.append("    wc)");
        builder.append("REFERENCES windcategory (idwc) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");

                builder.append(" FOREIGN KEY (");
                builder.append("    wd)");
        builder.append(" REFERENCES winddirection (idwd) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");

                builder.append("FOREIGN KEY (");
                builder.append("beach)");

        builder.append("REFERENCES beach (beach) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT)");

        return builder.toString();
    }

    public static String createTableTurtleNest(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE turtlenest (");
                builder.append(" idnest   INT (11) NOT NULL,");
        builder.append("idturtle INT (11) DEFAULT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append("idnest)");
        builder.append("FOREIGN KEY (");
                builder.append("idnest)");

        builder.append("REFERENCES nest (idnest) ON DELETE CASCADE " +
                "ON UPDATE CASCADE,");

                builder.append("FOREIGN KEY (");
                builder.append("idturtle)");

        builder.append("REFERENCES turtle (idturtle) ON DELETE CASCADE " +
                "ON UPDATE CASCADE)");

        return builder.toString();
    }

    public static String createTableNestWithouTurtle(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE nestwithoutturtle (");
                builder.append("idnest   INT (11) NOT NULL,");
        builder.append("idspecie INT (11) DEFAULT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append("idnest)");

        builder.append("FOREIGN KEY (");
                builder.append("idnest)");

                builder.append("REFERENCES nest (idnest) ON DELETE CASCADE " +
                        "ON UPDATE CASCADE,");

                builder.append("FOREIGN KEY (");
                builder.append("idspecie)");

        builder.append("REFERENCES specie (idspecie) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT)");

        return builder.toString();
    }

    public static String createTableObserver(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE observer (");
                builder.append("idobserver INTEGER      NOT NULL " +
                        "PRIMARY KEY AUTOINCREMENT,");

                builder.append("observer   VARCHAR (45) DEFAULT NULL)");

        return builder.toString();
    }

    public static String createTableObserverObservation(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE observerobservation (");
                builder.append("idturtle   INT (11)     NOT NULL,");
        builder.append("beach      VARCHAR (45) NOT NULL,");
        builder.append("dataa      DATETIME     NOT NULL,");
        builder.append("idobserver INT (11)     NOT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append("idturtle,");
                builder.append("beach,");
                builder.append("dataa,");
                builder.append("idobserver),");

        builder.append("FOREIGN KEY (");
                builder.append("dataa)");

        builder.append("REFERENCES observation (dataa) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");

                builder.append("FOREIGN KEY (");
                builder.append("idobserver)");

        builder.append("REFERENCES observer (idobserver) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");

                builder.append("FOREIGN KEY (");
                builder.append("idturtle)");

        builder.append("REFERENCES observation (idturtle) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");

                builder.append("FOREIGN KEY (");
                builder.append(" beach)");

        builder.append("REFERENCES observation (beach) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT");

        return builder.toString();
    }

    public static String createTableNestLocalization(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE nestlocalization (");
                builder.append("idnest       INT (11)      NOT NULL,");
        builder.append("dataa        DATETIME      NOT NULL,");
        builder.append("beach        VARCHAR (45)  DEFAULT NULL,");
        builder.append("idhabitat    INT (11)      DEFAULT NULL,");
        builder.append("distance     FLOAT         DEFAULT NULL,");
        builder.append("height       FLOAT         DEFAULT NULL,");
        builder.append("observations VARCHAR (255) DEFAULT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append(" idnest,");
                builder.append(" dataa),");

        builder.append("FOREIGN KEY (");
                builder.append("idhabitat)");

        builder.append("REFERENCES habitat (idhabitat) ON DELETE " +
                "ON UPDATE RESTRICT,");

                builder.append("FOREIGN KEY (");
                builder.append(" idnest)");

        builder.append("REFERENCES nest (idnest) ON DELETE CASCADE " +
                "ON UPDATE CASCADE,");

                builder.append("FOREIGN KEY (");
                builder.append("beach)");

        builder.append("REFERENCES beach (beach) ON DELETE RESTRICT ON UPDATE RESTRICT)");

        return builder.toString();
    }

    public static String createTableTurtleActivities(){
        StringBuilder builder = new StringBuilder();

                builder.append("CREATE TABLE turtleactivities (");
        builder.append("idturtle   INT (11)     NOT NULL,");
        builder.append("beach      VARCHAR (45) NOT NULL,");
        builder.append("idactivity INT (11)     NOT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append(" idturtle,");
                builder.append("beach,");
                builder.append(" dataa,");
                builder.append(" idactivity),");
        builder.append("FOREIGN KEY (");
                builder.append("dataa");

        builder.append("REFERENCES observation (dataa) ON DELETE RESTRICT");
        builder.append("ON UPDATE RESTRICT,");
                builder.append("FOREIGN KEY (");
                builder.append("idactivity)");

        builder.append("REFERENCES activities (idactivity) ON DELETE RESTRICT");
        builder.append("ON UPDATE RESTRICT,");
                builder.append("FOREIGN KEY (");
                builder.append("idturtle)");

        builder.append("REFERENCES observation (idturtle) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");
                builder.append("FOREIGN KEY (");
                builder.append("beach)");

        builder.append("REFERENCES observation (beach) ON DELETE RESTRICT ON UPDATE RESTRICT)");

        return builder.toString();
    }

    public static String createTableTurtleTaggs(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE turtletags (");
                builder.append("idturtle     INT (11) NOT NULL,");
        builder.append("dataa        DATETIME NOT NULL,");
        builder.append("leftring     INT (11) DEFAULT NULL,");
        builder.append("rightring    INT (11) DEFAULT NULL,");
        builder.append("internal_tag INT (11) DEFAULT NULL,");
        builder.append("ccl_measure  DOUBLE   DEFAULT NULL,");
        builder.append(" cwl_measure  DOUBLE   DEFAULT NULL,");
        builder.append(" PRIMARY KEY (");
                builder.append("idturtle),");
        builder.append(" FOREIGN KEY (");
        builder.append("idturtle)");

        builder.append("REFERENCES turtle (idturtle) ON DELETE CASCADE " +
                "ON UPDATE CASCADE)");

        return builder.toString();
    }

    public static String createTableLocalizationAndObservation(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE localizationandobservation (");
                builder.append("idnest            INT (11) NOT NULL,");
        builder.append("localization_date DATETIME NOT NULL,");
        builder.append("observation_date  DATETIME NOT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append("idnest,");
                builder.append("localization_date,");
                builder.append("observation_date),");

        builder.append("FOREIGN KEY (");
                builder.append("idnest)");

        builder.append("REFERENCES nestlocalization (idnest) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");

                builder.append("FOREIGN KEY (");
                builder.append(" localization_date)");

        builder.append("REFERENCES nestlocalization (dataa) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT)");

        return builder.toString();
    }

    public static String createTableHatchlings(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE hatchlings (");
                builder.append("idnest        INT (11)      NOT NULL,");
        builder.append("dataa         DATETIME      DEFAULT NULL,");
        builder.append("hatched       INT (11)      DEFAULT NULL,");
        builder.append("died_in_nest  INT (11)      DEFAULT NULL,");
        builder.append("alive_in_nest INT (11)      DEFAULT NULL,");
        builder.append("undeveloped   INT (11)      DEFAULT NULL,");
        builder.append("unhatched     INT (11)      DEFAULT NULL,");
        builder.append("predated_eggs INT (11)      DEFAULT NULL,");
        builder.append("description   VARCHAR (255) DEFAULT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append("idnest),");
        builder.append("FOREIGN KEY (");
                builder.append("idnest)");
        builder.append("REFERENCES nest (idnest) ON DELETE CASCADE " +
                "ON UPDATE CASCADE)");

        return builder.toString();
    }

    public static String createTableHabitat(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE habitat (");
                builder.append("idhabitat INTEGER      NOT NULL " +
                        "PRIMARY KEY AUTOINCREMENT,");
        builder.append("habitat   VARCHAR (45) DEFAULT NULL)");

        return builder.toString();
    }

    public static String createTableObservationAndObserver(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE observationandobserver (");
                builder.append("idnest            INT (11) NOT NULL,");
        builder.append("localization_date DATETIME NOT NULL,");
        builder.append("observation_date  DATETIME NOT NULL,");
        builder.append("idobserver        INT (11) NOT NULL,");
        builder.append("PRIMARY KEY (");
                builder.append("idnest,");
                builder.append("localization_date,");
                builder.append("observation_date,");
                builder.append("idobserver)");

        builder.append("FOREIGN KEY (");
                builder.append("idnest)");

        builder.append("REFERENCES localizationandobservation (idnest) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");
        builder.append("FOREIGN KEY (");
                builder.append("idobserver)");

        builder.append("REFERENCES observer (idobserver) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");

                builder.append("FOREIGN KEY (");
                builder.append("localization_date)");

        builder.append("REFERENCES localizationandobservation (localization_date) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT,");

                builder.append("FOREIGN KEY (");
                builder.append("observation_date)");

        builder.append("REFERENCES localizationandobservation (observation_date) ON DELETE RESTRICT " +
                "ON UPDATE RESTRICT");

        return builder.toString();
    }
}
