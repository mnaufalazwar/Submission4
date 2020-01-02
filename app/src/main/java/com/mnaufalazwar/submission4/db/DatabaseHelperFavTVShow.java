package com.mnaufalazwar.submission4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.mnaufalazwar.submission4.db.DatabaseContract.FavTVShowColumns.TABLE_NAME;

public class DatabaseHelperFavTVShow extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbfavtvshow";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FAV_TVSHOW = String.format("CREATE TABLE %s "
                    + " (%s INTEGER PRIMARY KEY,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.FavTVShowColumns.TV_ID,
            DatabaseContract.FavTVShowColumns.TITLE,
            DatabaseContract.FavTVShowColumns.OVERVIEW,
            DatabaseContract.FavTVShowColumns.POSTER,
            DatabaseContract.FavTVShowColumns.RELEASE,
            DatabaseContract.FavTVShowColumns.RATE);

    DatabaseHelperFavTVShow (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAV_TVSHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.FavMovieColumns.TABLE_NAME);
        onCreate(db);
    }
}
