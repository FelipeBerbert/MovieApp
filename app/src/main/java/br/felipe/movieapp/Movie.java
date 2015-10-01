package br.felipe.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by felip on 04/08/2015.
 */
public class Movie implements Parcelable {


    private String id;
    private String title;
    private String overview;
    private String release_date;
    private String vote_average;
    private String poster_path;

    public Movie(){

    }

    public Movie(Parcel parcel){
        String[] params = new String[6];
        parcel.readStringArray(params);
        this.id = params[0];
        this.title = params[1];
        this.overview = params[2];
        this.release_date = params[3];
        this.vote_average = params[4];
        this.poster_path = params[5];

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.id,this.title,this.overview,this.release_date,this.vote_average,this.poster_path});
    }
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){
        @Override
        public Movie createFromParcel(Parcel parcel){
            return new Movie(parcel);
        }
        @Override
        public Movie[] newArray(int size){
            return new Movie[size];
        }
    };
}
