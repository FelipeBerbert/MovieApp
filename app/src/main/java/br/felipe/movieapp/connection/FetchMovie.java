package br.felipe.movieapp.connection;

import android.os.AsyncTask;
import android.util.Log;

import br.felipe.movieapp.BuildConfig;
import br.felipe.movieapp.connection.MovieResponse;
import br.felipe.movieapp.connection.RestConnections;
import br.felipe.movieapp.interfaces.Connector;
import br.felipe.movieapp.utils.ServiceGenerator;

/**
 * Created by Felipe on 04/08/2015.
 */
public class FetchMovie extends AsyncTask<Void, Void, MovieResponse> {

    private Connector connector;
    private String order;

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public static final String TAG = "movieApp.FetchMovie";
    public static final String PARAMETER_VALUE_POP = "popularity.desc";
    public static final String PARAMETER_VALUE_RATING = "vote_average.desc";

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    protected MovieResponse doInBackground(Void... params) {

        try {
            RestConnections restConnections = ServiceGenerator.createService(RestConnections.class, Connector.API_URL);
            return restConnections.fetchMovies((order != null && !order.equals("") ? order : PARAMETER_VALUE_POP), BuildConfig.MOVIE_API_KEY);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(MovieResponse movieList) {
        super.onPostExecute(movieList);
        connector.onConnectionResult(movieList);
    }



}
