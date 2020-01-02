package com.mnaufalazwar.submission4.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mnaufalazwar.submission4.db.FavMovieHelper;

import static com.mnaufalazwar.submission4.db.DatabaseContract.AUTHORITY;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.CONTENT_URI;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.TABLE_NAME;

public class FavMovieProvider extends ContentProvider {

    private static final int FAVMOVIE = 1;
    private static final int FAVMOVIE_ID = 2;
    private FavMovieHelper favMovieHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVMOVIE);

        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", FAVMOVIE_ID);
    }

    public FavMovieProvider(){}

    @Override
    public boolean onCreate() {
        favMovieHelper = FavMovieHelper.getInstance(getContext());
        favMovieHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;
        switch (sUriMatcher.match(uri)){
            case FAVMOVIE:
                cursor = favMovieHelper.queryAll();
                break;
            case FAVMOVIE_ID:
                cursor = favMovieHelper.queryById(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long added;
        switch (sUriMatcher.match(uri)){
            case FAVMOVIE:
                added = favMovieHelper.insert(values);
                break;
            default:
                added = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int updated;
        switch (sUriMatcher.match(uri)){
            case FAVMOVIE_ID:
                updated = favMovieHelper.update(uri.getLastPathSegment(), values);
                break;
            default:
                updated = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return updated;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int deleted;
        switch (sUriMatcher.match(uri)){
            case FAVMOVIE_ID:
                deleted = favMovieHelper.deleteById(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return deleted;
    }
}
