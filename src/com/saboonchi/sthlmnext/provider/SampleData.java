package com.saboonchi.sthlmnext.provider;

import android.database.MatrixCursor;

public class SampleData {
    private static final String RIGHT_ARROW = " \u2192 ";

    
  //-------------------------  Stations -------------------------
    public static MatrixCursor sampleStations() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "name", "type", "distance" });
        cursor.addRow(new Object[] { 0, "Kista", "TB", "0.5km" });
        cursor.addRow(new Object[] { 1, "Husby", "T", "1.0km" });
        cursor.addRow(new Object[] { 2, "Helenelund", "J", "1.5km" });
        return cursor;
    }
    
    //-------------------------------------------------------------
    
    //------------------------- Favorites -------------------------

    public static MatrixCursor sampleFavorites() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "name", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Kista" + RIGHT_ARROW + "Akalla" ,"Akalla", "T 11", "3min" });
        cursor.addRow(new Object[] { 1, "Husby" + RIGHT_ARROW + "Brommaplan","Brommaplan", "B 509", "10min" });
        return cursor;
    }
    
    //-------------------------  Destination -------------------------
    
    public static MatrixCursor sampleDestination_Kista() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Kungstr�gatan", "T 11", "3min" });
        cursor.addRow(new Object[] { 1, "Akalla", "T 11", "5min" });
        cursor.addRow(new Object[] { 2, "M�rby Station", "B 177", "7min" });
        cursor.addRow(new Object[] { 3, "T�by Centrum", "B 177", "10min" });
        return cursor;
    }
    
    
    public static MatrixCursor sampleDestination_Husby() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Kungstr�gatan", "T 11", "3min" });
        cursor.addRow(new Object[] { 1, "Akalla", "T 11", "5min" });
        cursor.addRow(new Object[] { 2, "Danderyds sjukhus", "B 509", "7min" });
        cursor.addRow(new Object[] { 3, "Brommaplan", "B 509", "10min" });
        return cursor;
    }
    
    public static MatrixCursor sampleDestination_Helenelund() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Kungstr�gatan", "T 11", "3min" });
        cursor.addRow(new Object[] { 1, "Akalla", "T 11", "5min" });
        cursor.addRow(new Object[] { 2, "J�rfella", "B 200", "7min" });
        cursor.addRow(new Object[] { 3, "Upplands v�sby", "B 200", "10min" });
        return cursor;
    }
    
  //-------------------------------------------------------------
    
    
  //------------------------- Departure -------------------------
    
    public static MatrixCursor sampleDeparture_Kungstr�gatan() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Kungstr�gatan", "T 11", "Nu" });
        cursor.addRow(new Object[] { 1, "Kungstr�gatan", "T 11", "5min" });
        cursor.addRow(new Object[] { 2, "Kungstr�gatan", "T 11", "15min" });
        cursor.addRow(new Object[] { 3, "Kungstr�gatan", "T 11", "15min" });
        return cursor;
    }
    
    public static MatrixCursor sampleDeparture_Akalla() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Akalla", "T 11", "Nu" });
        cursor.addRow(new Object[] { 1, "Akalla", "T 11", "5min" });
        cursor.addRow(new Object[] { 2, "Akalla", "T 11", "15min" });
        cursor.addRow(new Object[] { 3, "Akalla", "T 11", "15min" });
        return cursor;
    }

    public static MatrixCursor sampleDeparture_M�rbyStation() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "M�rby Station", "B 177", "Nu" });
        cursor.addRow(new Object[] { 1, "M�rby Station", "B 177", "5min" });
        cursor.addRow(new Object[] { 2, "M�rby Station", "B 177", "15min" });
        cursor.addRow(new Object[] { 3, "M�rby Station", "B 177", "15min" });
        return cursor;
    }
    
    public static MatrixCursor sampleDeparture_T�byCentrum() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "T�by Centrum", "B 177", "Nu" });
        cursor.addRow(new Object[] { 1, "T�by Centrum", "B 177", "5min" });
        cursor.addRow(new Object[] { 2, "T�by Centrum", "B 177", "15min" });
        cursor.addRow(new Object[] { 3, "T�by Centrum", "B 177", "15min" });
        return cursor;
    }

    public static MatrixCursor sampleDeparture_Danderydssjukhus() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Danderyds sjukhus", "B 509", "Nu" });
        cursor.addRow(new Object[] { 1, "Danderyds sjukhus", "B 509", "5min" });
        cursor.addRow(new Object[] { 2, "Danderyds sjukhus", "B 509", "15min" });
        cursor.addRow(new Object[] { 3, "Danderyds sjukhus", "B 509", "15min" });
        return cursor;
    }

    public static MatrixCursor sampleDeparture_Brommaplan() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Brommaplan", "B 509", "Nu" });
        cursor.addRow(new Object[] { 1, "Brommaplan", "B 509", "5min" });
        cursor.addRow(new Object[] { 2, "Brommaplan", "B 509", "15min" });
        cursor.addRow(new Object[] { 3, "Brommaplan", "B 509", "15min" });
        return cursor;
    }

    public static MatrixCursor sampleDeparture_J�rfella() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "J�rfella", "B 200", "Nu" });
        cursor.addRow(new Object[] { 1, "J�rfella", "B 200", "5min" });
        cursor.addRow(new Object[] { 2, "J�rfella", "B 200", "15min" });
        cursor.addRow(new Object[] { 3, "J�rfella", "B 200", "15min" });
        return cursor;
    }

    
    public static MatrixCursor sampleDeparture_Upplandsv�sby() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "destination", "line", "time" });
        cursor.addRow(new Object[] { 0, "Upplands v�sby", "B 200", "Nu" });
        cursor.addRow(new Object[] { 1, "Upplands v�sby", "B 200", "5min" });
        cursor.addRow(new Object[] { 2, "Upplands v�sby", "B 200", "15min" });
        cursor.addRow(new Object[] { 3, "Upplands v�sby", "B 200", "15min" });
        return cursor;
    }

    
    //-------------------------------------------------------------



}
