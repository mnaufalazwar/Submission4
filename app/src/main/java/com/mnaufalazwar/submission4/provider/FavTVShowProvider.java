package com.mnaufalazwar.submission4.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mnaufalazwar.submission4.db.FavTVShowHelper;

import static com.mnaufalazwar.submission4.db.DatabaseContract.AUTHORITY;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavTVShowColumns.CONTENT_URI;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavTVShowColumns.TABLE_NAME;

public class FavTVShowProvider extends ContentProvider {

    private static final int FAVTV = 1;
    private static final int FAVTV_ID = 2;
    private FavTVShowHelper favTVShowHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVTV);

        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", FAVTV_ID);
    }

    public FavTVShowProvider(){}

    @Override
    public boolean onCreate() {
        favTVShowHelper = FavTVShowHelper.getInstance(getContext());
        favTVShowHelper.open();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;
        switch (sUriMatcher.match(uri)){
            case FAVTV:
                cursor = favTVShowHelper.queryAll();
                break;
            case FAVTV_ID:
                cursor = favTVShowHelper.queryById(uri.getLastPathSegment());
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
            case FAVTV:
                added = favTVShowHelper.insert(values);
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
            case FAVTV_ID:
                updated = favTVShowHelper.update(uri.getLastPathSegment(), values);
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
            case FAVTV_ID:
                deleted = favTVShowHelper.deleteById(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, null);

        return deleted;
    }
}
