package com.mnaufalazwar.submission4.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mnaufalazwar.submission4.R;
import com.mnaufalazwar.submission4.db.FavMovieHelper;
import com.mnaufalazwar.submission4.db.FavTVShowHelper;
import com.mnaufalazwar.submission4.model.Movie;
import com.mnaufalazwar.submission4.ui.movie.MovieFragment;
import com.mnaufalazwar.submission4.ui.tvshow.TVShowFragment;

import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.MOVIE_ID;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.OVERVIEW;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.POSTER;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.RATE;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.RELEASE;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavMovieColumns.TITLE;
import static com.mnaufalazwar.submission4.db.DatabaseContract.FavTVShowColumns.TV_ID;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle, tvOverview, tvRelease, tvRate;
    ImageView ivPoster, btnFav;
    Button btnFavorite;

    public static final String EXTRA_DATA = "extra_data";
    public static final String EXTRA_TYPE = "extra_type";

    private FavMovieHelper favMovieHelper;
    private FavTVShowHelper favTVShowHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.description));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvReleaseDate);
        tvRate = findViewById(R.id.tvRate);
        ivPoster = findViewById(R.id.ivPoster);
        btnFavorite = findViewById(R.id.btnFavorite);

        final Movie movie = getIntent().getParcelableExtra(EXTRA_DATA);

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvRelease.setText(movie.getReleaseDate());
        tvRate.setText("" + movie.getRate());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + movie.getPoster())
                .into(ivPoster);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
//                values.put(MOVIE_ID, movie.getId());
                values.put(TITLE, movie.getTitle());
                values.put(OVERVIEW, movie.getOverview());
                values.put(POSTER, movie.getPoster());
                values.put(RELEASE, movie.getReleaseDate());
                values.put(RATE, movie.getRate());

                String type = getIntent().getStringExtra(EXTRA_TYPE);
                if (type != null) {
                    if (type.equals(MovieFragment.TYPE)) {
                        values.put(MOVIE_ID, movie.getId());
                        favMovieHelper = FavMovieHelper.getInstance(getApplicationContext());
                        favMovieHelper.open();
                        favMovieHelper.insert(values);
                        Toast.makeText(DetailActivity.this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show();
                    }
                    if (type.equals(TVShowFragment.TYPE)) {
                        values.put(TV_ID, movie.getId());
                        favTVShowHelper = FavTVShowHelper.getInstance(getApplicationContext());
                        favTVShowHelper.open();
                        favTVShowHelper.insert(values);
                        Toast.makeText(DetailActivity.this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
