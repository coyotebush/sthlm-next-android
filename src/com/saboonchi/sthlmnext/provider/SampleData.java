package com.saboonchi.sthlmnext.provider;

import android.database.MatrixCursor;

public class SampleData {
    private static final String RIGHT_ARROW = " \u2192 ";

    public static MatrixCursor sampleStations() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "name", "type", "distance" });
        cursor.addRow(new Object[] { 0, "Kista", "TB", "0.5km" });
        cursor.addRow(new Object[] { 1, "Husby", "T", "1.0km" });
        cursor.addRow(new Object[] { 2, "Helenelund", "J", "1.5km" });
        return cursor;
    }

    public static MatrixCursor sampleFavorites() {
        MatrixCursor cursor = new MatrixCursor(new String[] { "_id", "name", "line", "time" });
        cursor.addRow(new Object[] { 0, "Kista" + RIGHT_ARROW + "Akalla", "T 11", "3min" });
        cursor.addRow(new Object[] { 1, "Solna" + RIGHT_ARROW + "Danderyds sjukhus", "B 509", "10min" });
        return cursor;
    }
}
