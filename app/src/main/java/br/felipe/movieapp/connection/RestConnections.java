package br.felipe.movieapp.connection;


import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by felip on 30/09/2015.
 */
public interface RestConnections {
    @GET("/3/discover/movie")
    MovieResponse fetchMovies(@Query("sort_by") String sort,
                              @Query("api_key") String apiKey);


}
