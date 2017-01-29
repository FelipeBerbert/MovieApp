package br.felipe.movieapp.MovieList;

import java.util.List;

import br.felipe.movieapp.BasePresenter;
import br.felipe.movieapp.BaseView;
import br.felipe.movieapp.models.Movie;

/**
 * Created by Felipe Berbert on 29/01/2017.
 */

public interface MovieListContract {

    interface Presenter extends BasePresenter {
        void result(int requestCode, int resultCode);
        void loadMovieList(String order);
        void openMovieDetails();
        void loadFavoriteMovies();


    }

    interface View extends BaseView<Presenter>{
        void setLoadingIndicator(boolean isLoading);
        void showMovieList(List<Movie> movies);
        void showNoMovies();
    }

}
