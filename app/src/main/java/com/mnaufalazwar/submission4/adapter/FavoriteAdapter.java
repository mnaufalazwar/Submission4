package com.mnaufalazwar.submission4.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mnaufalazwar.submission4.R;
import com.mnaufalazwar.submission4.custom.CustomOnItemClickListener;
import com.mnaufalazwar.submission4.model.FavoriteModel;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private final ArrayList<FavoriteModel> list = new ArrayList<>();
    private final Activity activity;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback{
        void onItemClicked(FavoriteModel data, int position);
    }

    public FavoriteAdapter (Activity activity){
        this.activity = activity;
    }

    public ArrayList<FavoriteModel> getList(){
        return list;
    }

    public void setList(ArrayList<FavoriteModel> list){

        if(list.size() > 0){
            this.list.clear();
        }

        this.list.addAll(list);

        notifyDataSetChanged();
    }

    public void addItem(FavoriteModel favoriteModel) {
        this.list.add(favoriteModel);
        notifyItemInserted(list.size() - 1);
    }

    public void updateItem(int position, FavoriteModel favoriteModel) {
        this.list.set(position, favoriteModel);
        notifyItemChanged(position, favoriteModel);
    }

    public void removeItem(int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {

        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvOverview.setText(list.get(position).getOverview());

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185" + list.get(position).getPoster())
                .apply(new RequestOptions().override(55,55))
                .into(holder.ivPoster);

        holder.btnDelete.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                onItemClickCallback.onItemClicked(list.get(position), position);
            }
        }));

        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {

            }
        }));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        final TextView tvTitle, tvOverview;
        final ImageView ivPoster, btnDelete;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            ivPoster = itemView.findViewById(R.id.img_item_photo);

        }
    }
}
