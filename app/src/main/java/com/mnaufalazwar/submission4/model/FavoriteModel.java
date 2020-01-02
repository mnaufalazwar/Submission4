package com.mnaufalazwar.submission4.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteModel implements Parcelable {

    private int id;
    private String title;
    private String overview;
    private String poster;
    private String releaseDate;
    private int rate;

    public FavoriteModel() {
    }

    public FavoriteModel(int id, String title, String overview, String poster, String releaseDate, int rate) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.poster = poster;
        this.releaseDate = releaseDate;
        this.rate = rate;
    }

    protected FavoriteModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        poster = in.readString();
        releaseDate = in.readString();
        rate = in.readInt();
    }

    public static final Creator<FavoriteModel> CREATOR = new Creator<FavoriteModel>() {
        @Override
        public FavoriteModel createFromParcel(Parcel in) {
            return new FavoriteModel(in);
        }

        @Override
        public FavoriteModel[] newArray(int size) {
            return new FavoriteModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(poster);
        dest.writeString(releaseDate);
        dest.writeInt(rate);
    }
}
