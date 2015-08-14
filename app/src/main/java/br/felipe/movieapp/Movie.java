package br.felipe.movieapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by felip on 04/08/2015.
 */
public class Movie implements Parcelable {


    private String id;
    private String title;
    private String synopsis;
    private String releaseDate;
    private String rating;
    private String posterUrl;

    public Movie(){

    }

    public Movie(Parcel parcel){
        String[] params = new String[6];
        parcel.readStringArray(params);
        this.id = params[0];
        this.title = params[1];
        this.synopsis = params[2];
        this.releaseDate = params[3];
        this.rating = params[4];
        this.posterUrl = params[5];

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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
        dest.writeStringArray(new String[]{this.id,this.title,this.synopsis,this.releaseDate,this.rating,this.posterUrl});
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
