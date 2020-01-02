package com.mnaufalazwar.submission4.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.TABLE_NAME;

public class DatabaseHelperFavMovie extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbfavmovie";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_FAV_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL,"
                    + " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.FavMovieColumns.MOVIE_ID,
            DatabaseContract.FavMovieColumns.TITLE,
            DatabaseContract.FavMovieColumns.OVERVIEW,
            DatabaseContract.FavMovieColumns.POSTER,
            DatabaseContract.FavMovieColumns.RELEASE,
            DatabaseContract.FavMovieColumns.RATE);

    DatabaseHelperFavMovie(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_FAV_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
