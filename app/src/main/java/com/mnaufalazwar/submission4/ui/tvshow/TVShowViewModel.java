package com.mnaufalazwar.submission4.ui.tvshow;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.mnaufalazwar.submission4.BuildConfig;
import com.mnaufalazwar.submission4.model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TVShowViewModel extends ViewModel {

    private static final String API_KEY = BuildConfig.TMDB_API_KEY;
    private MutableLiveData<ArrayList<Movie>> listTvShow = new MutableLiveData<>();

    void setListTVShow(){

        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("HASIL TV : ", result);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    Log.d("N LIST TV : ", ""+list.length());

                    for (int i = 0 ; i < list.length() ; i ++){
                        JSONObject data = list.getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setId(i);
                        movie.setTitle(data.getString("name"));
                        movie.setOverview(data.getString("overview"));
                        movie.setPoster(data.getString("poster_path"));
                        movie.setRate(data.getInt("vote_average"));
                        movie.setReleaseDate(data.getString("first_air_date"));

                        listItems.add(movie);
                    }
                    listTvShow.postValue(listItems);
                }
                catch (Exception e){
                    Log.d("Exceptoin huhu ", e.getMessage());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

    }

    LiveData<ArrayList<Movie>> getListTVShow(){
        return listTvShow;
    }


}
