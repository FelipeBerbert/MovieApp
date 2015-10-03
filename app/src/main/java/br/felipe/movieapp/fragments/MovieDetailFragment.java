package br.felipe.movieapp.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import br.felipe.movieapp.adapters.ReviewAdapter;
import br.felipe.movieapp.adapters.TrailerAdapter;
import br.felipe.movieapp.connection.FetchMovieInfo;
import br.felipe.movieapp.models.Movie;
import br.felipe.movieapp.R;
import br.felipe.movieapp.interfaces.Connector;
import br.felipe.movieapp.models.MovieInfo;
import br.felipe.movieapp.models.Review;
import br.felipe.movieapp.models.Video;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment implements Connector {

    public static final String MOVIE = "movie";

    Movie movie;
    TextView movieTitle;
    ImageView moviePoster;
    TextView movieRating;
    TextView movieSynopsis;
    TextView movieRelease;
    LinearLayout listTrailersLl;
    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;
    LinearLayout trailersLl;
    LinearLayout listReviewsLl;
    LinearLayout reviewsLl;

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        Bundle args = getArguments();
        if(args != null){
            movie = args.getParcelable(MOVIE);
        }
        //movie = getActivity().getIntent().getParcelableExtra(getString(R.string.app_package) + ".MovieObject");

        movieTitle = (TextView) rootView.findViewById(R.id.movie_title_text);
        moviePoster = (ImageView) rootView.findViewById(R.id.movie_poster_iv);
        movieRating = (TextView) rootView.findViewById(R.id.movie_rating);
        movieSynopsis = (TextView) rootView.findViewById(R.id.movie_synopse_text);
        movieRelease = (TextView) rootView.findViewById(R.id.movie_release_text);
        listTrailersLl = (LinearLayout) rootView.findViewById(R.id.list_trailers_ll);
        trailersLl = (LinearLayout) rootView.findViewById(R.id.trailers_ll);
        listReviewsLl = (LinearLayout) rootView.findViewById(R.id.list_reviews_ll);
        reviewsLl = (LinearLayout) rootView.findViewById(R.id.reviews_ll);


        trailerAdapter = new TrailerAdapter(getActivity(), R.layout.trailer_item_view);
        reviewAdapter = new ReviewAdapter(getActivity(), R.layout.review_item_view);
        //trailersLv.setAdapter(trailerAdapter);

        setViews();
        if (movie != null)
            getMovieInfo(movie.getId());
        return rootView;
    }

    private void getMovieInfo(String id) {
        FetchMovieInfo fmi = new FetchMovieInfo();
        fmi.setConnector(this);
        fmi.execute(id);
    }

    private void setViews() {
        if(movie != null) {
            movieTitle.setText(movie.getTitle());
            Picasso.with(getActivity()).load(Connector.BASE_POSTER_URL_BIG + movie.getPoster_path())
                    .placeholder(getActivity().getResources().getDrawable(R.mipmap.loading))
                    .error(getActivity().getResources().getDrawable(R.mipmap.error)).into(moviePoster);
            movieRelease.setText(movie.getRelease_date());
            movieRating.setText(movie.getVote_average());
            movieSynopsis.setText(movie.getOverview());
        }
    }


    @Override
    public void onConnectionResult(Object movieInfo) {

        Video[] trailers = ((MovieInfo) movieInfo).getVideoResponse();
        Review[] reviews = ((MovieInfo) movieInfo).getReviewResponse();
        if(trailers != null && trailers.length > 0) {
            trailersLl.setVisibility(View.VISIBLE);
            for (Video video : trailers) {
                if(video.getSite().toLowerCase().equals("youtube")) {
                    trailerAdapter.add(video);
                    trailersLl.addView(trailerAdapter.getView(trailerAdapter.getCount() - 1, null, null));
                }
            }
        }
        if(reviews != null && reviews.length > 0) {
            reviewsLl.setVisibility(View.VISIBLE);
            for (Review review : reviews) {
                reviewAdapter.add(review);
                reviewsLl.addView(reviewAdapter.getView(reviewAdapter.getCount()-1, null, null));
            }
        }
    }
}
