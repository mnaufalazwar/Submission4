package com.mnaufalazwar.submission4.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mnaufalazwar.submission4.R;
import com.mnaufalazwar.submission4.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final ArrayList<Movie> listMovie = new ArrayList<>();
    private final Activity activity;
    private OnItemClickCallback onItemClickCallback;
    private OnItemFavoriteCallback onItemFavoriteCallback;

    public MovieAdapter(Activity activity){
        this.activity = activity;
    }

    public ArrayList<Movie> getListMovie(){
        return listMovie;
    }

    public void setListMovie(ArrayList<Movie> listMovie){

        if(listMovie.size() > 0){
            this.listMovie.clear();
        }
        this.listMovie.addAll(listMovie);

        notifyDataSetChanged();
    }

    public void updateItem(int position, Movie movie){
        this.listMovie.set(position, movie);
        notifyItemChanged(position, movie);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback{
        void onItemClicked(Movie data);
    }

    public void setOnItemFavoriteCallback(OnItemFavoriteCallback onItemFavoriteCallback){
        this.onItemFavoriteCallback = onItemFavoriteCallback;
    }

    public interface OnItemFavoriteCallback{
        void onItemFavorited(Movie data, int position);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {

        holder.tvTitle.setText(listMovie.get(position).getTitle());
        holder.tvOverview.setText(listMovie.get(position).getOverview());
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185" + listMovie.get(position).getPoster())
                .apply(new RequestOptions().override(55,55))
                .into(holder.ivPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMovie.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        final TextView tvTitle, tvOverview;
        final ImageView ivPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            ivPoster = itemView.findViewById(R.id.img_item_photo);

        }
    }
}
