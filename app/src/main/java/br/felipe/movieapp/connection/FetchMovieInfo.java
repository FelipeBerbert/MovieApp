package br.felipe.movieapp.connection;

import android.os.AsyncTask;
import android.util.Log;

import br.felipe.movieapp.BuildConfig;
import br.felipe.movieapp.interfaces.Connector;
import br.felipe.movieapp.models.MovieInfo;
import br.felipe.movieapp.utils.ServiceGenerator;

/**
 * Created by felip on 01/10/2015.
 */
public class FetchMovieInfo extends AsyncTask<String, Void, MovieInfo>{

    private Connector connector;

    public static final String TAG = "movieApp.FetchMovieInfo";

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    @Override
    protected MovieInfo doInBackground(String... params) {
        try {
            RestConnections restConnections = ServiceGenerator.createService(RestConnections.class, Connector.API_URL);
            String id = params[0];
            VideoResponse videoResponse = restConnections.fetchVideos(id, BuildConfig.MOVIE_API_KEY);
            ReviewResponse reviewResponse = restConnections.fetchReviews(id, BuildConfig.MOVIE_API_KEY);
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
