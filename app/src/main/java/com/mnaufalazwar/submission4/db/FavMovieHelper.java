package com.mnaufalazwar.submission4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.MOVIE_ID;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.TABLE_NAME;

public class FavMovieHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelperFavMovie databaseHelper;
    private static FavMovieHelper INSTANCE;

    private static SQLiteDatabase database;

    private FavMovieHelper(Context context){
        databaseHelper = new DatabaseHelperFavMovie(context);
    }

    public static FavMovieHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new FavMovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close(){
        databaseHelper.close();

        if(database.isOpen()){
            database.close();
        }
    }

    public Cursor queryAll(){
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.FavMovieColumns.MOVIE_ID + " ASC"
        );
    }

    public Cursor queryById(String id){
        return database.query(DATABASE_TABLE, null,
                DatabaseContract.FavMovieColumns.MOVIE_ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insert(ContentValues values){
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int update(String id, ContentValues values){
        return database.update(DATABASE_TABLE, values, MOVIE_ID + " = ?", new String[]{id});
    }

    public int deleteById(String id){
        return database.delete(DATABASE_TABLE, MOVIE_ID + " = ?", new String[]{id});
    }
}
