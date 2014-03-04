package com.saboonchi.sthlmnext.database;

import com.saboonchi.sthlmnext.model.Destination;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

public class FavoritesDBAdapter {
    protected static final String TABLE_NAME = "favorites";
    public static final String COL_STATIONID = "stationId";
    public static final String COL_STATIONNAME = "stationName";
    public static final String COL_DESTINATIONNAME = "destinationName";
    public static final String COL_LINETYPE = "lineType";
    public static final String COL_LINENUMBER = "lineNumber";
    protected static final String[] ALL_COLUMNS = {
        BaseColumns._ID,
        COL_STATIONID,
        COL_STATIONNAME,
        COL_DESTINATIONNAME,
        COL_LINETYPE,
        COL_LINENUMBER
    };

    protected static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY,"
            + COL_STATIONID + " INTEGER,"
            + COL_STATIONNAME + " TEXT,"
            + COL_DESTINATIONNAME + " TEXT,"
            + COL_LINETYPE + " TEXT,"
            + COL_LINENUMBER + " INTEGER"
            + ")";
    protected static final String SQL_DROP_TABLE =
            "DROP TABLE " + TABLE_NAME;
    protected static final String SQL_WHERE_CLAUSE =
            COL_STATIONID + "=? " + "AND "
            + COL_DESTINATIONNAME + "=? " + "AND "
            + COL_LINENUMBER + "=?";

    protected static final Uri URI_FAVORITES =
            Uri.parse("content://com.saboonchi.sthlmnext/favorites");

    protected class DatabaseHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 4;
        public static final String DATABASE_NAME = "STHLMNext.db";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
            insertSampleData(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO actually upgrade the data
            db.execSQL(SQL_DROP_TABLE);
            onCreate(db);
        }
    }

    protected Context context;
    protected DatabaseHelper helper;
    protected static FavoritesDBAdapter instance;

    protected FavoritesDBAdapter(Context ctx) {
        context = ctx;
        helper = new DatabaseHelper(context);
    }

    public static FavoritesDBAdapter getInstance(Context ctx) {
        if (instance == null) {
            instance = new FavoritesDBAdapter(ctx);
        }
        return instance;
    }

    public void close() {
        helper.close();
    }

    public Cursor getAllFavorites() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, ALL_COLUMNS, null, null, null, null, COL_STATIONNAME);
        c.setNotificationUri(context.getContentResolver(), URI_FAVORITES);
        return c;
    }

    public boolean isFavorite(Destination dest) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query(
                TABLE_NAME,
                new String[] { BaseColumns._ID },
                SQL_WHERE_CLAUSE,
                new String[] { dest.stationId, dest.destinationName,
                        dest.lineNumber }, null, null, null);
        return c.getCount() > 0;
    }

    public long addFavorite(Destination dest) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_STATIONID, dest.stationId);
        values.put(COL_STATIONNAME, dest.stationName);
        values.put(COL_DESTINATIONNAME, dest.destinationName);
        values.put(COL_LINETYPE, dest.lineType);
        values.put(COL_LINENUMBER, dest.lineNumber);

        long rowId = db.insertOrThrow(TABLE_NAME, null, values);
        context.getContentResolver().notifyChange(URI_FAVORITES, null);
        return rowId;
    }
    
    public int removeFavorite(Destination dest) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int rowCount = db.delete(TABLE_NAME, SQL_WHERE_CLAUSE, new String[] { dest.stationId,
                        dest.destinationName, dest.lineNumber });
        context.getContentResolver().notifyChange(URI_FAVORITES, null);
        return rowCount;
    }

    protected void insertSampleData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(COL_STATIONID, 0);
        values.put(COL_STATIONNAME, "Kista");
        values.put(COL_DESTINATIONNAME, "Kungsträdgården");
        values.put(COL_LINETYPE, "T");
        values.put(COL_LINENUMBER, 11);

        db.insertOrThrow(TABLE_NAME, null, values);
    }

}
