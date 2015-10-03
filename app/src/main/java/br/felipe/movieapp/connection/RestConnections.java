package br.felipe.movieapp.connection;


import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Felipe on 30/09/2015.
 */
public interface RestConnections {
    @GET("/discover/movie")
    MovieResponse fetchMovies(@Query("sort_by") String sort,
                              @Query("api_key") String apiKey);

    @GET("/movie/{id}/videos")
    VideoResponse fetchVideos(@Path("id") String id,
                              @Query("api_key") String apiKey);

    @GET("/movie/{id}/reviews")
    ReviewResponse fetchReviews(@Path("id") String id,
                                @Query("api_key") String apiKey);


}
