package br.felipe.movieapp.interfaces;

import br.felipe.movieapp.connection.MovieResponse;

/**
 * Created by felipe on 05/08/2015.
 *
 */
public interface Connector {

    String BASE_POSTER_URL_BIG = "http://image.tmdb.org/t/p/w500/";
    String BASE_POSTER_URL_MEDIUM = "http://image.tmdb.org/t/p/w342/";
    String BASE_POSTER_URL_SMALL = "http://image.tmdb.org/t/p/w185/";
    String JSON_RESULT = "results";
    String JSON_SYNOPSIS = "overview";
    String JSON_TITLE = "title";
    String JSON_ID = "id";
    String JSON_POSTER_URL = "poster_path";
    String JSON_RELEASE_DATE = "release_date";
    String JSON_RATING = "vote_average";


    void onConnectionResult(MovieResponse Movies);

}
