package br.felipe.movieapp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import br.felipe.movieapp.Movie;
import br.felipe.movieapp.R;
import br.felipe.movieapp.interfaces.Connector;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    Movie movie;

    TextView movieTitle;
    ImageView moviePoster;
    TextView movieRating;
    TextView movieSynopsis;
    TextView movieRelease;

    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        movie = getActivity().getIntent().getParcelableExtra(getString(R.string.app_package)+".MovieObject");
        movieTitle = (TextView) rootView.findViewById(R.id.movie_title_text);
        moviePoster = (ImageView) rootView.findViewById(R.id.movie_poster_iv);
        movieRating = (TextView) rootView.findViewById(R.id.movie_rating);
        movieSynopsis = (TextView) rootView.findViewById(R.id.movie_synopse_text);
        movieRelease = (TextView) rootView.findViewById(R.id.movie_release_text);
        setViews();
        return rootView;
    }

    private void setViews() {
        if(movie != null) {
            movieTitle.setText(movie.getTitle());
            Picasso.with(getActivity()).load(Connector.BASE_POSTER_URL + movie.getPosterUrl()).placeholder(getActivity().getResources().getDrawable(R.mipmap.loading)).error(getActivity().getResources().getDrawable(R.mipmap.error)).into(moviePoster);
            movieRelease.setText(movie.getReleaseDate());
            movieRating.setText(movie.getRating());
            movieSynopsis.setText(movie.getSynopsis());
        }
    }


}
