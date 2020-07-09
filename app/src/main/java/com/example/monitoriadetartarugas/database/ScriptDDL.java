package com.example.monitoriadetartarugas.database;

public class ScriptDDL {

    public static String createTableActivities(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS activities(");
        builder.append("    idactivity INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        builder.append("    activity VARCHAR (45) NOT NULL DEFAULT (''));");

        return builder.toString();
    }

    public static String createTableBeach(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS beach(");
        builder.append("    beach  VARCHAR (45) NOT NULL PRIMARY KEY,");
        builder.append("    island VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    FOREIGN KEY(");
        builder.append("        island)");
        builder.append("    REFERENCES island (island));");

        return builder.toString();
    }

    public static String createTableTurtle(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS turtle(");
        builder.append("    idturtle INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,");
        builder.append("    idspecie INT (11) NOT NULL DEFAULT (''),");
        builder.append("    notes VARCHAR (255) NOT NULL DEFAULT (''),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idspecie)");
        builder.append("    REFERENCES specie (idspecie));");

        return builder.toString();
    }

    public static String createTableSpecie(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS specie(");
        builder.append("    idspecie INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        builder.append("    specie VARCHAR (45) NOT NULL DEFAULT (''));");

        return builder.toString();
    }

    public static String createTableNest(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS nest(");
        builder.append("    idnest INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        builder.append("    depth  INT (11) NOT NULL DEFAULT (''),");
        builder.append("    eggs_quantity INT (11) NOT NULL DEFAULT (''),");
        builder.append("    distance_to_tide FLOAT NOT NULL DEFAULT (''),");
        builder.append("    notes VARCHAR (255) NOT NULL DEFAULT (''));");

        return builder.toString();
    }

    public static String createTableWindCategory(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS windcategory(");
        builder.append("    idwc INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        builder.append("    name VARCHAR (45) NOT NULL DEFAULT (''));");

        return builder.toString();
    }

    public static String createTableWindDirection(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS winddirection(");
        builder.append("    idwd INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        builder.append("    name VARCHAR (45) NOT NULL DEFAULT (''));");

        return builder.toString();
    }

    public static String createTableIsland(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS island(");
        builder.append("    island VARCHAR (45) NOT NULL PRIMARY KEY);");

        return builder.toString();
    }

    public static String createTableUsers(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS users(");
        builder.append("    email VARCHAR (255) NOT NULL PRIMARY KEY,");
        builder.append("    password VARCHAR (255) NOT NULL DEFAULT (''));");

        return builder.toString();
    }

    public static String createTableObservation(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS observation(");
        builder.append("    idturtle INT (11) NOT NULL DEFAULT (''),");
        builder.append("    beach VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    dataa DATETIME NOT NULL DEFAULT (''),");
        builder.append("    idactivity INT (11) NOT NULL DEFAULT (''),");
        builder.append("    wc INT (11) NOT NULL DEFAULT (''),");
        builder.append("    wd INT (11) NOT NULL DEFAULT (''),");
        builder.append("    dune_height FLOAT NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idturtle,");
        builder.append("        beach,");
        builder.append("        dataa),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idturtle)");
        builder.append("    REFERENCES turtle (idturtle),");
        builder.append("    FOREIGN KEY (");
        builder.append("        wc)");
        builder.append("    REFERENCES windcategory (idwc),");
        builder.append("    FOREIGN KEY(");
        builder.append("        wd)");
        builder.append("    REFERENCES winddirection (idwd),");
        builder.append("    FOREIGN KEY (");
        builder.append("        beach)");
        builder.append("    REFERENCES beach (beach),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idactivity)");
        builder.append("    REFERENCES activities (idactivity));");

        return builder.toString();
    }

    public static String createTableTurtleNest(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS turtlenest(");
        builder.append("    idnest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    idturtle INT (11) NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idnest),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idnest)");
        builder.append("    REFERENCES nest (idnest),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idturtle)");
        builder.append("    REFERENCES turtle (idturtle));");

        return builder.toString();
    }

    public static String createTableTurtleNestData(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS turtlenestdata(");
        builder.append("    idturtle INT (11) NOT NULL DEFAULT (''),");
        builder.append("    idnest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    depth  INT (11) NOT NULL DEFAULT (''),");
        builder.append("    eggs_quantity INT (11) NOT NULL DEFAULT (''),");
        builder.append("    distance_to_tide FLOAT NOT NULL DEFAULT (''),");
        builder.append("    hatch_dataa DATETIME NOT NULL DEFAULT (''),");
        builder.append("    hatched INT (11) NOT NULL DEFAULT (''),");
        builder.append("    died_in_nest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    alive_in_nest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    undeveloped INT (11) NOT NULL DEFAULT (''),");
        builder.append("    unhatched INT (11) NOT NULL DEFAULT (''),");
        builder.append("    predated_eggs INT (11) NOT NULL DEFAULT (''),");
        builder.append("    nest_marking_date DATETIME NOT NULL DEFAULT (''),");
        builder.append("    nest_beach VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    habitat VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    gps_east FLOAT NOT NULL DEFAULT (''),");
        builder.append("    gps_south FLOAT NOT NULL DEFAULT (''),");
        builder.append("    tagg_dataa DATETIME NOT NULL DEFAULT (''),");
        builder.append("    leftring INT (11) NOT NULL DEFAULT (''),");
        builder.append("    rightring INT (11) NOT NULL DEFAULT (''),");
        builder.append("    internal_tag INT (11) NOT NULL DEFAULT (''),");
        builder.append("    ccl_measure DOUBLE NOT NULL DEFAULT (''),");
        builder.append("    cwl_measure DOUBLE NOT NULL DEFAULT (''),");
        builder.append("    obs_beach VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    obs_dataa DATETIME NOT NULL DEFAULT (''),");
        builder.append("    activity VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    wc VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    wd VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    dune_height FLOAT NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY(");
        builder.append("        idturtle,");
        builder.append("        idnest));");

        return builder.toString();
    }

    public static String createTableNestWithouTurtle(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS nestwithoutturtle (");
        builder.append("    idnest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    idspecie INT (11) NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idnest),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idnest)");
        builder.append("    REFERENCES nest (idnest),");
        builder.append("    FOREIGN KEY(");
        builder.append("        idspecie)");
        builder.append("    REFERENCES specie (idspecie));");

        return builder.toString();
    }

    public static String createTableNestWTurtleData(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS nestWTurtleData (");
        builder.append("    idnest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    specie VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    habitat VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    beach VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    gps_east FLOAT NOT NULL DEFAULT (''),");
        builder.append("    gps_south FLOAT NOT NULL DEFAULT (''),");
        builder.append("    nest_loc_date DATETIME NOT NULL DEFAULT (''),");
        builder.append("    depth INT (11) NOT NULL DEFAULT (''),");
        builder.append("    eggs_quantity INT (11) NOT NULL DEFAULT (''),");
        builder.append("    distance_to_tide FLOAT NOT NULL DEFAULT (''),");
        builder.append("    nest_notes VARCHAR (255) NOT NULL DEFAULT (''),");
        builder.append("    hatched INT (11) NOT NULL DEFAULT (''),");
        builder.append("    hatch_dataa DATETIME NOT NULL DEFAULT (''),");
        builder.append("    died_in_nest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    alive_in_nest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    undeveloped INT (11) NOT NULL DEFAULT (''),");
        builder.append("    predated_eggs INT (11) NOT NULL DEFAULT (''),");
        builder.append("    unhatched INT (11) NOT NULL DEFAULT (''),");
        builder.append("    hatch_notes VARCHAR (255) NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idnest,");
        builder.append("        specie));");

        return builder.toString();
    }

    public static String createTableObserver(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS observer(");
        builder.append("    idobserver INTEGER PRIMARY KEY AUTOINCREMENT,");
        builder.append("    observer VARCHAR (45) NOT NULL DEFAULT (''));");

        return builder.toString();
    }

    public static String createTableObserverObservation(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS observerobservation (");
        builder.append("    idturtle INT (11) NOT NULL DEFAULT (''),");
        builder.append("    beach VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    dataa DATETIME  NOT NULL DEFAULT (''),");
        builder.append("    idobserver INT (11) NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idturtle,");
        builder.append("        beach,");
        builder.append("        dataa,");
        builder.append("        idobserver),");
        builder.append("    FOREIGN KEY (");
        builder.append("        dataa)");
        builder.append("    REFERENCES observation (dataa),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idobserver)");
        builder.append("    REFERENCES observer (idobserver),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idturtle)");
        builder.append("    REFERENCES observation (idturtle),");
        builder.append("    FOREIGN KEY (");
        builder.append("        beach)");
        builder.append("    REFERENCES observation (beach));");

        return builder.toString();
    }

    public static String createTableNestLocalization(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS nestlocalization (");
        builder.append("    idnest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    nest_marking_date DATETIME NOT NULL DEFAULT (''),");
        builder.append("    beach VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    idhabitat INT (11) NOT NULL DEFAULT (''),");
        builder.append("    gps_east FLOAT NOT NULL DEFAULT (''),");
        builder.append("    gps_south FLOAT NOT NULL DEFAULT (''),");
        builder.append("    notes VARCHAR (255) NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idnest,");
        builder.append("        nest_marking_date),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idhabitat)");
        builder.append("    REFERENCES habitat (idhabitat),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idnest)");
        builder.append("    REFERENCES nest (idnest),");
        builder.append("    FOREIGN KEY (");
        builder.append("        beach)");
        builder.append("    REFERENCES beach (beach));");

        return builder.toString();
    }

    public static String createTableTurtleActivities(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS turtleactivities (");
        builder.append("    idturtle INT (11) NOT NULL DEFAULT (''),");
        builder.append("    beach VARCHAR (45) NOT NULL DEFAULT (''),");
        builder.append("    idactivity INT (11) NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idturtle,");
        builder.append("        beach,");
        builder.append("        idactivity),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idactivity)");
        builder.append("    REFERENCES activities (idactivity),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idturtle)");
        builder.append("    REFERENCES observation (idturtle),");
        builder.append("    FOREIGN KEY (");
        builder.append("        beach)");
        builder.append("    REFERENCES observation (beach));");

        return builder.toString();
    }

    public static String createTableTurtleTaggs(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS turtletags (");
        builder.append("    idturtle INT (11) NOT NULL DEFAULT (''),");
        builder.append("    dataa DATETIME NOT NULL DEFAULT (''),");
        builder.append("    leftring INT (11) NOT NULL DEFAULT (''),");
        builder.append("    rightring INT (11) NOT NULL DEFAULT (''),");
        builder.append("    internal_tag INT (11) NOT NULL DEFAULT (''),");
        builder.append("    ccl_measure DOUBLE NOT NULL DEFAULT (''),");
        builder.append("    cwl_measure DOUBLE NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idturtle),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idturtle)");
        builder.append("    REFERENCES turtle (idturtle));");

        return builder.toString();
    }

    public static String createTableLocalizationAndObservation(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS localizationandobservation (");
        builder.append("    idnest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    nest_marking_date DATETIME NOT NULL DEFAULT (''),");
        builder.append("    observation_date  DATETIME NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idnest,");
        builder.append("        nest_marking_date,");
        builder.append("        observation_date),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idnest)");
        builder.append("    REFERENCES nestlocalization (idnest),");
        builder.append("    FOREIGN KEY (");
        builder.append("        nest_marking_date)");
        builder.append("    REFERENCES nestlocalization (dataa));");

        return builder.toString();
    }

    public static String createTableHatchlings(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS hatchlings (");
        builder.append("    idnest  INT (11) NOT NULL DEFAULT (''),");
        builder.append("    dataa DATETIME NOT NULL DEFAULT (''),");
        builder.append("    hatched INT (11) NOT NULL DEFAULT (''),");
        builder.append("    died_in_nest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    alive_in_nest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    undeveloped INT (11) NOT NULL DEFAULT (''),");
        builder.append("    unhatched INT (11) NOT NULL DEFAULT (''),");
        builder.append("    predated_eggs INT (11) NOT NULL DEFAULT (''),");
        builder.append("    description VARCHAR (255) NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idnest),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idnest)");
        builder.append("    REFERENCES nest (idnest));");

        return builder.toString();
    }

    public static String createTableHabitat(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS habitat (");
        builder.append("    idhabitat INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        builder.append("    habitat VARCHAR (45) NOT NULL DEFAULT (''));");

        return builder.toString();
    }

    public static String createTableObservationAndObserver(){
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS observationandobserver (");
        builder.append("    idnest INT (11) NOT NULL DEFAULT (''),");
        builder.append("    nest_marking_date DATETIME NOT NULL DEFAULT (''),");
        builder.append("    observation_date DATETIME NOT NULL DEFAULT (''),");
        builder.append("    idobserver INT (11) NOT NULL DEFAULT (''),");
        builder.append("    PRIMARY KEY (");
        builder.append("        idnest,");
        builder.append("        nest_marking_date,");
        builder.append("        observation_date,");
        builder.append("        idobserver)");
        builder.append("    FOREIGN KEY (");
        builder.append("        idnest)");
        builder.append("    REFERENCES localizationandobservation (idnest),");
        builder.append("    FOREIGN KEY (");
        builder.append("        idobserver)");
        builder.append("    REFERENCES observer (idobserver),");
        builder.append("    FOREIGN KEY (");
        builder.append("        nest_marking_date)");
        builder.append("    REFERENCES localizationandobservation (nest_marking_date),");
        builder.append("    FOREIGN KEY (");
        builder.append("        observation_date)");
        builder.append("    REFERENCES localizationandobservation (observation_date));");

        return builder.toString();
    }

    public static String dropTableTurtle(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS turtle");
        return builder.toString();
    }

    public static String dropTableSpecie(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS specie");
        return builder.toString();
    }

    public static String dropTableBeach(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS beach");
        return builder.toString();
    }

    public static String dropTableIsland(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS island");
        return builder.toString();
    }

    public static String dropTableActivities(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS activities");
        return builder.toString();
    }

    public static String dropTableHabitat(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS habitat");
        return builder.toString();
    }

    public static String dropTableWC(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS windcategory");
        return builder.toString();
    }

    public static String dropTableWD(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS winddirection");
        return builder.toString();
    }

    public static String dropTableNest(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS nest");
        return builder.toString();
    }

    public static String dropTableNestlocalization(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS nestlocalization");
        return builder.toString();
    }

    public static String dropTableLocalizationandobservation(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS localizationandobservation");
        return builder.toString();
    }

    public static String dropTableHatchlings(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS hatchlings");
        return builder.toString();
    }

    public static String dropTableTurtletags(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS turtletags");
        return builder.toString();
    }

    public static String dropTableTurtlenest(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS turtlenest");
        return builder.toString();
    }

    public static String dropTableNestwithoutturtle(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS nestwithoutturtle");
        return builder.toString();
    }

    public static String dropTableObserver(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS observer");
        return builder.toString();
    }

    public static String dropTableObservation(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS observation");
        return builder.toString();
    }

    public static String dropTableTurtleactivities(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS turtleactivities");
        return builder.toString();
    }

    public static String dropTableObserverobservation(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS observerobservation");
        return builder.toString();
    }

    public static String dropTableObservationAndObserver(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS observationandobserver");
        return builder.toString();
    }

    public static String dropTableUsers(){
        StringBuilder builder = new StringBuilder();
        builder.append("DROP TABLE IF EXISTS users");
        return builder.toString();
    }
}
