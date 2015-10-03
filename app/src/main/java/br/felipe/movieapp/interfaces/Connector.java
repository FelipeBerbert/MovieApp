package br.felipe.movieapp.interfaces;

import java.util.Objects;

import br.felipe.movieapp.connection.MovieResponse;

/**
 * Created by felipe on 05/08/2015.
 *
 */
public interface Connector {

    String API_URL = "https://api.themoviedb.org/3";
    String BASE_POSTER_URL_BIG = "http://image.tmdb.org/t/p/w500/";
    String BASE_POSTER_URL_MEDIUM = "http://image.tmdb.org/t/p/w342/";
    String BASE_POSTER_URL_SMALL = "http://image.tmdb.org/t/p/w185/";
    String YOUTUBE_THUMB_URL = "https://img.youtube.com/vi/%s/0.jpg";
    String YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v=";



    void onConnectionResult(Object object);

}
