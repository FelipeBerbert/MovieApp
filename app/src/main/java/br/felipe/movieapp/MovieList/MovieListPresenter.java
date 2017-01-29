package br.felipe.movieapp.MovieList;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.felipe.movieapp.connection.FetchMovie;
import br.felipe.movieapp.connection.MovieResponse;
import br.felipe.movieapp.interfaces.Connector;
import br.felipe.movieapp.models.Movie;
import br.felipe.movieapp.provider.movie.MovieCursor;
import br.felipe.movieapp.provider.movie.MovieSelection;

import static br.felipe.movieapp.MovieList.MovieGridFragment.PARAMETER_VALUE_FAVORITES;
import static br.felipe.movieapp.MovieList.MovieGridFragment.PREF_ORDER;

/**
 * Created by Felipe Berbert on 29/01/2017.
 */

public class MovieListPresenter implements MovieListContract.Presenter {

    private final MovieListContract.View movieListView;

    private Context context;

    public MovieListPresenter(MovieListContract.View movieListView, Context context) {
        this.movieListView = movieListView;
        this.context = context;

        movieListView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadMovieList(String order) {
        movieListView.setLoadingIndicator(true);
        FetchMovie fm = new FetchMovie(new Connector() {
            @Override
            public void onConnectionResult(Object movieList) {
                if (movieList != null && ((MovieResponse)movieList).results.length >  0) {
//                    filmGrid.setVisibility(View.VISIBLE);
//                    noDataText.setVisibility(View.GONE);
                    processMovieList(Arrays.asList(((MovieResponse)movieList).results));
                }else{
//                    checkConnection();
                }
            }
        }, order);
        fm.execute();
    }

    private void processMovieList(List<Movie> movies) {
        if (movies.isEmpty()) {
            movieListView.showNoMovies();
        } else {
            movieListView.showMovieList(movies);
        }
    }

    @Override
    public void openMovieDetails() {

    }

    @Override
    public void loadFavoriteMovies() {
        MovieSelection where = new MovieSelection();
        MovieCursor cursor = where.query(context.getContentResolver());
        List<Movie> movieList = new ArrayList<>();
        if(cursor.moveToFirst()) {
            movieList.add(cursor.getMovie());
            while (cursor.moveToNext()) {
                movieList.add(cursor.getMovie());
            }
        }
        cursor.close();
        processMovieList(movieList);
    }

    @Override
    public void start() {
        String order = PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_ORDER, FetchMovie.PARAMETER_VALUE_POP);
        if(order.equals(PARAMETER_VALUE_FAVORITES)){
            loadFavoriteMovies();
        }else {
//            checkConnection();
//            adapter.clear();
//            FetchMovie fm = new FetchMovie(this, order);
//            fm.execute();
            loadMovieList(order);
        }
    }
}
