package com.mnaufalazwar.submission4.ui.tvshow;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mnaufalazwar.submission4.R;
import com.mnaufalazwar.submission4.activity.DetailActivity;
import com.mnaufalazwar.submission4.adapter.MovieAdapter;
import com.mnaufalazwar.submission4.model.Movie;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFragment extends Fragment {

    private ProgressBar progressBar;
    public static final String TYPE = TVShowFragment.class.getSimpleName();

    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rvTVShow);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final MovieAdapter adapter = new MovieAdapter(getActivity());
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {

                Toast.makeText(getContext(), data.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA, data);
                intent.putExtra(DetailActivity.EXTRA_TYPE, TYPE);
                startActivity(intent);
            }
        });

        adapter.setOnItemFavoriteCallback(new MovieAdapter.OnItemFavoriteCallback() {
            @Override
            public void onItemFavorited(Movie data, int position) {

                Movie movie = data;
                if(movie.isFavorite()){
                    movie.setFavorite(false);
                }else {
                    movie.setFavorite(true);
                }

                adapter.updateItem(position, movie);
            }
        });

        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        TVShowViewModel tvShowViewModel = new ViewModelProvider(
                getActivity(),
                new ViewModelProvider.NewInstanceFactory())
                .get(TVShowViewModel.class);

        tvShowViewModel.setListTVShow();

        showLoading(true);

        tvShowViewModel.getListTVShow().observe(getActivity(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                adapter.setListMovie(movies);
                showLoading(false);
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
