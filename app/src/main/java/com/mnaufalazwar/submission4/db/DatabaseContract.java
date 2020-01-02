package com.mnaufalazwar.submission4.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {

    public static final String AUTHORITY = "com.mnaufalazwar.submission4";
    private static final String SCHEME = "content";

    public static final class FavMovieColumns implements BaseColumns{
        public static final String TABLE_NAME = "fav_movie";

        public static final String MOVIE_ID = "movie_id";

        public static final String TITLE = "title";

        public static final String OVERVIEW = "overview";

        public static final String POSTER = "poster";

        public static final String RELEASE = "release";

        public static final String RATE = "rate";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static final class FavTVShowColumns implements BaseColumns{
        public static final String TABLE_NAME = "fav_tvshow";

        public static final String TV_ID = "tv_id";

        public static final String TITLE = "title";

        public static final String OVERVIEW = "overview";

        public static final String POSTER = "poster";

        public static final String RELEASE = "release";

        public static final String RATE = "rate";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

}
