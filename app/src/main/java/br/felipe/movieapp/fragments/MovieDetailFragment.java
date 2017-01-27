package br.felipe.movieapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


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
import br.felipe.movieapp.provider.movie.MovieColumns;
import br.felipe.movieapp.provider.movie.MovieContentValues;
import br.felipe.movieapp.provider.movie.MovieCursor;
import br.felipe.movieapp.provider.movie.MovieSelection;
import br.felipe.movieapp.provider.review.ReviewColumns;
import br.felipe.movieapp.provider.review.ReviewContentValues;
import br.felipe.movieapp.provider.review.ReviewCursor;
import br.felipe.movieapp.provider.review.ReviewSelection;
import br.felipe.movieapp.provider.video.VideoColumns;
import br.felipe.movieapp.provider.video.VideoContentValues;
import br.felipe.movieapp.provider.video.VideoCursor;
import br.felipe.movieapp.provider.video.VideoSelection;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailFragment extends Fragment implements Connector {

    public static final String MOVIE = "movie";
    public static final String SHARE_TEXT = "Watch this trailer for %s! ";

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
    LinearLayout favoriteLl;
    TextView favoriteText;

    boolean isFavorite;
    private ShareActionProvider shareActionProvider;
    private String videoShareString;

    public MovieDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        Bundle args = getArguments();
        if (args != null) {
            movie = args.getParcelable(MOVIE);
        }
        //movie = getActivity().getIntent().getParcelableExtra(getString(R.string.app_package) + ".MovieObject");

        initiateViews(rootView);

        trailerAdapter = new TrailerAdapter(getActivity(), R.layout.trailer_item_view);
        reviewAdapter = new ReviewAdapter(getActivity(), R.layout.review_item_view);
        //trailersLv.setAdapter(trailerAdapter);

        if (movie != null){
            isFavorite();
            setViews();
            getMovieInfo(movie.getId());
        }
        setHasOptionsMenu(true);
        return rootView;
    }

    private void initiateViews(View rootView) {
        movieTitle = (TextView) rootView.findViewById(R.id.movie_title_text);
        moviePoster = (ImageView) rootView.findViewById(R.id.movie_poster_iv);
        movieRating = (TextView) rootView.findViewById(R.id.movie_rating);
        movieSynopsis = (TextView) rootView.findViewById(R.id.movie_synopse_text);
        movieRelease = (TextView) rootView.findViewById(R.id.movie_release_text);
        listTrailersLl = (LinearLayout) rootView.findViewById(R.id.list_trailers_ll);
        trailersLl = (LinearLayout) rootView.findViewById(R.id.trailers_ll);
        listReviewsLl = (LinearLayout) rootView.findViewById(R.id.list_reviews_ll);
        reviewsLl = (LinearLayout) rootView.findViewById(R.id.reviews_ll);
        favoriteLl = (LinearLayout) rootView.findViewById(R.id.favorite_ll);
        favoriteText = (TextView) rootView.findViewById(R.id.favorite_text);
    }

    private void getMovieInfo(long id) {
        if (isFavorite) {
            VideoSelection videoWhere = new VideoSelection();
            videoWhere.movieId(movie.getInternal_id());
            VideoCursor videoCursor = videoWhere.query(getActivity().getContentResolver());
            Video[] videos = new Video[videoCursor.getCount()];
            for (int i = 0; videoCursor.moveToNext(); i++) {
                videos[i] = videoCursor.getVideo();
            }
            videoCursor.close();
            fillTrailersView(videos);

            ReviewSelection reviewWhere = new ReviewSelection();
            reviewWhere.movieId(movie.getInternal_id());
            ReviewCursor revCursor = reviewWhere.query(getActivity().getContentResolver());
            Review[] reviews = new Review[revCursor.getCount()];
            for (int i = 0; revCursor.moveToNext(); i++) {
                reviews[i] = revCursor.getReview();
            }
            revCursor.close();
            fillReviewsView(reviews);
        } else {
            FetchMovieInfo fmi = new FetchMovieInfo(this);
            fmi.execute(id);
        }
    }

    private void setViews() {
        if (movie != null) {
            movieTitle.setText(movie.getTitle());
            Picasso.with(getActivity()).load(Connector.BASE_POSTER_URL_BIG + movie.getPoster_path())
                    .placeholder(getActivity().getResources().getDrawable(R.mipmap.loading))
                    .error(getActivity().getResources().getDrawable(R.mipmap.error)).into(moviePoster);
            movieRelease.setText(movie.getRelease_date());
            movieRating.setText(movie.getVote_average());
            movieSynopsis.setText(movie.getOverview());
            setViewFavorite();
            favoriteLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleFavorite();
                }
            });

        }
    }

    private void setViewFavorite() {
        if (isFavorite) {
            favoriteText.setText(getString(R.string.delete_favorite_label));
            favoriteLl.setBackgroundColor(getResources().getColor(R.color.material_red_light));
        } else {
            favoriteText.setText(getString(R.string.mark_favorite_label));
            favoriteLl.setBackgroundColor(getResources().getColor(R.color.material_indigo));
        }
    }

    private void toggleFavorite() {
        if (!isFavorite) {
            MovieContentValues movieValues = new MovieContentValues();
            movieValues.putMovie(movie);
            Uri newRow = getActivity().getContentResolver().insert(MovieColumns.CONTENT_URI, movieValues.values());
            if(newRow == null){
                Toast.makeText(getActivity(),getString(R.string.insert_error),Toast.LENGTH_LONG).show();
                return;
            }
            long newMovieId = Long.valueOf(newRow.toString().substring(newRow.toString().lastIndexOf("/")+1));
            movie.setInternal_id(newMovieId);
            for (int i = 0; i < trailerAdapter.getCount(); i++) {
                VideoContentValues videoValues = new VideoContentValues();
                videoValues.putVideo(trailerAdapter.getItem(i), newMovieId);
                getActivity().getContentResolver().insert(VideoColumns.CONTENT_URI, videoValues.values());
            }
            for (int i = 0; i < reviewAdapter.getCount(); i++) {
                ReviewContentValues reviewValues = new ReviewContentValues();
                reviewValues.putReview(reviewAdapter.getItem(i), newMovieId);
                getActivity().getContentResolver().insert(ReviewColumns.CONTENT_URI, reviewValues.values());
            }
            isFavorite = true;
        } else {
            VideoSelection videoWhere = new VideoSelection();
            videoWhere.movieId(movie.getInternal_id());
            videoWhere.delete(getActivity().getContentResolver());
            ReviewSelection reviewWhere = new ReviewSelection();
            reviewWhere.movieId(movie.getInternal_id());
            reviewWhere.delete(getActivity().getContentResolver());
            MovieSelection where = new MovieSelection();
            where.externalId(movie.getId());
            where.delete(getActivity().getContentResolver());
            isFavorite = false;
        }
        setViewFavorite();
    }

    private boolean isFavorite() {
        MovieSelection where = new MovieSelection();
        where.externalId(movie.getId());
        MovieCursor cursor = where.query(getActivity().getContentResolver());
        isFavorite = cursor.moveToFirst();
        if(isFavorite) movie.setInternal_id(cursor.getId());
        cursor.close();
        return isFavorite;
    }


    @Override
    public void onConnectionResult(Object movieInfo) {
        Video[] trailers = ((MovieInfo) movieInfo).getVideoResponse();
        Review[] reviews = ((MovieInfo) movieInfo).getReviewResponse();
        fillTrailersView(trailers);
        fillReviewsView(reviews);

    }

    private void fillTrailersView(Video[] trailers) {
        if (trailers != null && trailers.length > 0) {
            trailersLl.setVisibility(View.VISIBLE);
            videoShareString = String.format(SHARE_TEXT, movie.getTitle()) + Connector.YOUTUBE_VIDEO_URL + trailers[0].getKey();
            for (Video video : trailers) {
                if (video.getSite().toLowerCase().equals("youtube")) {
                    trailerAdapter.add(video);
                    trailersLl.addView(trailerAdapter.getView(trailerAdapter.getCount() - 1, null, null));
                }
            }
        }
    }

    private void fillReviewsView(Review[] reviews) {
        if (reviews != null && reviews.length > 0) {
            reviewsLl.setVisibility(View.VISIBLE);
            for (Review review : reviews) {
                reviewAdapter.add(review);
                reviewsLl.addView(reviewAdapter.getView(reviewAdapter.getCount() - 1, null, null));
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_detail_fragment, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);

        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(setShareIntent());
        }
    }

    public Intent setShareIntent() {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, videoShareString);
        return sendIntent;
    }

}
