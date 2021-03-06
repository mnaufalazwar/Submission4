package com.mnaufalazwar.submission4.ui.movie;


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
public class MovieFragment extends Fragment {

    private ProgressBar progressBar;
    public static final String TYPE = MovieFragment.class.getSimpleName();

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rvMovie);
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

        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        MovieViewModel movieViewModel = new ViewModelProvider(getActivity(),
                new ViewModelProvider.NewInstanceFactory())
                .get(MovieViewModel.class);

        movieViewModel.setListMovie();
        showLoading(true);

        movieViewModel.getListMovie().observe(getActivity(), new Observer<ArrayList<Movie>>() {
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
