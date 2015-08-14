package br.felipe.movieapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import br.felipe.movieapp.interfaces.Connector;

/**
 * Created by Felipe on 04/08/2015.
 */
public class FetchMovie extends AsyncTask<Void, Void, ArrayList<Movie>>{

    private Connector connector;
    private String order;

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    static final String TAG = "movieApp.FetchMovie";
    static final String API_URL = "https://api.themoviedb.org/";
    static final String PARAMETER_DISCOVER = "3/discover/movie";
    static final String PARAMETER_SORT = "sort_by";
    static final String PARAMETER_VALUE_POP = "popularity.desc";
    static final String PARAMETER_VALUE_RATING = "vote_average.desc";
    static final String PARAMETER_API = "api_key";

    public void setOrder(String order){
        this.order = order;
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... params) {

        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String responseJsonString;

        Uri.Builder uri = Uri.parse(API_URL).buildUpon();
        uri.path(PARAMETER_DISCOVER);
        uri.appendQueryParameter(PARAMETER_SORT, (order != null && !order.equals("") ? order : PARAMETER_VALUE_POP));
        uri.appendQueryParameter(PARAMETER_API, BuildConfig.MOVIE_API_KEY);

        try {
            URL url = new URL(uri.build().toString());
            Log.i(TAG, url.toString());

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            responseJsonString = buffer.toString();
            Log.i(TAG, responseJsonString);


        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        try {
            return getMoviesFromJson(responseJsonString);
        }catch(Exception e){
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movieList) {
        super.onPostExecute(movieList);
        connector.onConnectionResult(movieList);
    }


    private ArrayList<Movie> getMoviesFromJson(String responseJsonString) throws JSONException {


        JSONObject movieJson = new JSONObject(responseJsonString);
        JSONArray jsonMovieList = movieJson.getJSONArray(Connector.JSON_RESULT);

        ArrayList<Movie> movieList = new ArrayList<>();

        for(int i = 0; i < jsonMovieList.length(); i++){
            Movie movie = new Movie();
            movie.setId(jsonMovieList.getJSONObject(i).getString(Connector.JSON_ID));
            movie.setPosterUrl(jsonMovieList.getJSONObject(i).getString(Connector.JSON_POSTER_URL));
            movie.setRating(jsonMovieList.getJSONObject(i).getString(Connector.JSON_RATING));
            movie.setReleaseDate(jsonMovieList.getJSONObject(i).getString(Connector.JSON_RELEASE_DATE));
            movie.setSynopsis(jsonMovieList.getJSONObject(i).getString(Connector.JSON_SYNOPSIS));
            movie.setTitle(jsonMovieList.getJSONObject(i).getString(Connector.JSON_TITLE));
            movieList.add(movie);
        }

        return movieList;
    }

}
