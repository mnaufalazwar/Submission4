package com.mnaufalazwar.submission4.helper;

import android.database.Cursor;

import com.mnaufalazwar.submission4.db.DatabaseContract;
import com.mnaufalazwar.submission4.model.FavoriteModel;
import com.mnaufalazwar.submission4.model.Movie;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<FavoriteModel> mapCursorToArrayList(Cursor cursor){
        ArrayList<FavoriteModel> favoriteModels = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.MOVIE_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.TITLE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.OVERVIEW));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.POSTER));
            int rate = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.RATE));
            String release = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.RELEASE));

            favoriteModels.add(new FavoriteModel(id, title, overview, poster, release, rate));
        }

        return favoriteModels;
    }

    public static ArrayList<FavoriteModel> mapCursorToArrayListTV(Cursor cursor){
        ArrayList<FavoriteModel> favoriteModels = new ArrayList<>();

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavTVShowColumns.TV_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavTVShowColumns.TITLE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavTVShowColumns.OVERVIEW));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavTVShowColumns.POSTER));
            int rate = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavTVShowColumns.RATE));
            String release = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavTVShowColumns.RELEASE));

            favoriteModels.add(new FavoriteModel(id, title, overview, poster, release, rate));
        }

        return favoriteModels;
    }

    public static FavoriteModel mapCursorToObject(Cursor cursor){

        cursor.moveToFirst();

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns._ID));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.TITLE));
        String overview = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.OVERVIEW));
        String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.POSTER));
        int rate = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.RATE));
        String release = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.RELEASE));

        return new FavoriteModel(id, title, overview, poster, release, rate);
    }
}
