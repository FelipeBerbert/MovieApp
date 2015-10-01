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
    public static final String API_URL = "https://api.themoviedb.org";
    public static final String PARAMETER_DISCOVER = "3/discover/movie";
    public static final String PARAMETER_TRAILERS = "movie/{id}/videos ";
    public static final String PARAMETER_REVIEWS = "movie/{id}/reviews";
    public static final String PARAMETER_SORT = "sort_by";
    public static final String PARAMETER_VALUE_POP = "popularity.desc";
    public static final String PARAMETER_VALUE_RATING = "vote_average.desc";
    public static final String PARAMETER_API = "api_key";

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    protected MovieResponse doInBackground(Void... params) {

        //HttpURLConnection conn = null;
        //BufferedReader reader = null;
        //String responseJsonString;

        /*Uri.Builder uri = Uri.parse(API_URL).buildUpon();
        uri.path(PARAMETER_DISCOVER);
        uri.appendQueryParameter(PARAMETER_SORT, (order != null && !order.equals("") ? order : PARAMETER_VALUE_POP));
        uri.appendQueryParameter(PARAMETER_API, BuildConfig.MOVIE_API_KEY);
        URL url = new URL(uri.build().toString());
        Log.i(TAG, url.toString());*/

        try {
            RestConnections restConnections = ServiceGenerator.createService(RestConnections.class, API_URL);
            return restConnections.fetchMovies((order != null && !order.equals("") ? order : PARAMETER_VALUE_POP), BuildConfig.MOVIE_API_KEY);
            /*
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
            return getMoviesFromJson(responseJsonString);*/
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


    /*private ArrayList<Movie> getMoviesFromJson(String responseJsonString) throws JSONException {


        JSONObject movieJson = new JSONObject(responseJsonString);
        JSONArray jsonMovieList = movieJson.getJSONArray(Connector.JSON_RESULT);

        ArrayList<Movie> movieList = new ArrayList<>();

        for (int i = 0; i < jsonMovieList.length(); i++) {
            Movie movie = new Movie();
            movie.setId(jsonMovieList.getJSONObject(i).getString(Connector.JSON_ID));
            movie.setPoster_path(jsonMovieList.getJSONObject(i).getString(Connector.JSON_POSTER_URL));
            movie.setVote_average(jsonMovieList.getJSONObject(i).getString(Connector.JSON_RATING));
            movie.setRelease_date(jsonMovieList.getJSONObject(i).getString(Connector.JSON_RELEASE_DATE));
            movie.setOverview(jsonMovieList.getJSONObject(i).getString(Connector.JSON_SYNOPSIS));
            movie.setTitle(jsonMovieList.getJSONObject(i).getString(Connector.JSON_TITLE));
            movieList.add(movie);
        }

        return movieList;
    }*/

}
