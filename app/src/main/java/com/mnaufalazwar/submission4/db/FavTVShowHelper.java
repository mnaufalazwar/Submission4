package com.mnaufalazwar.submission4.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.provider.BaseColumns._ID;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavTVShowColumns.TABLE_NAME;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavTVShowColumns.TV_ID;


public class FavTVShowHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelperFavTVShow databaseHelper;
    private static FavTVShowHelper INSTANCE;

    private static SQLiteDatabase database;

    private FavTVShowHelper(Context context){
        databaseHelper = new DatabaseHelperFavTVShow(context);
    }

    public static FavTVShowHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new FavTVShowHelper(context);
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
                DatabaseContract.FavTVShowColumns.TV_ID + " ASC"
        );
    }

    public Cursor queryById(String id){
        return database.query(DATABASE_TABLE, null,
                DatabaseContract.FavTVShowColumns.TV_ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insert(ContentValues values){
        if(database == null){
            Log.d("error astaga", "tadaa");
            return database.insert(DATABASE_TABLE, null, values);
        }else {
            return database.insert(DATABASE_TABLE, null, values);
        }

    }

    public int update(String id, ContentValues values){
        return database.update(DATABASE_TABLE, values, TV_ID + " = ?", new String[]{id});
    }

    public int deleteById(String id){
        return database.delete(DATABASE_TABLE, TV_ID + " = ?", new String[]{id});
    }
}
