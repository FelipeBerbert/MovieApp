package br.felipe.movieapp.connection;

import android.os.AsyncTask;
import android.util.Log;

import br.felipe.movieapp.BuildConfig;
import br.felipe.movieapp.interfaces.Connector;
import br.felipe.movieapp.models.MovieInfo;
import br.felipe.movieapp.models.Review;
import br.felipe.movieapp.models.Video;
import br.felipe.movieapp.utils.ServiceGenerator;

/**
 * Created by felip on 01/10/2015.
 */
public class FetchMovieInfo extends AsyncTask<Long, Void, MovieInfo>{

    public static final String TAG = "movieApp.FetchMovieInfo";

    private Connector connector;

    public FetchMovieInfo(Connector connector) {
        this.connector = connector;
    }

    @Override
    protected MovieInfo doInBackground(Long... params) {
        try {
            RestConnections restConnections = ServiceGenerator.createService(RestConnections.class, Connector.API_URL);
            Long id = params[0];
            VideoResponse videoResponse = restConnections.fetchVideos(id, BuildConfig.MOVIE_API_KEY);
            ReviewResponse reviewResponse = restConnections.fetchReviews(id, BuildConfig.MOVIE_API_KEY);

            //Workarround The API does not put the movie ID in each object.
            /*for(Video video : videoResponse.getResults()){
                video.setMovieId(videoResponse.getId());
            }
            for(Review review : reviewResponse.getResults()){
                review.setMovieId(reviewResponse.getId());
            }*/

            return new MovieInfo(reviewResponse.getResults(), videoResponse.getResults());

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(MovieInfo movieInfo) {
        super.onPostExecute(movieInfo);
        connector.onConnectionResult(movieInfo);
    }
}
