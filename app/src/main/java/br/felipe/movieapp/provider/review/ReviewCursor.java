/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.review;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.felipe.movieapp.provider.base.AbstractCursor;
import br.felipe.movieapp.provider.movie.*;

/**
 * Cursor wrapper for the {@code review} table.
 */
public class ReviewCursor extends AbstractCursor implements ReviewModel {
    public ReviewCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(ReviewColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code review_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getReviewId() {
        String res = getStringOrNull(ReviewColumns.REVIEW_ID);
        if (res == null)
            throw new NullPointerException("The value of 'review_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Author of the review.
     * Can be {@code null}.
     */
    @Nullable
    public String getAuthor() {
        String res = getStringOrNull(ReviewColumns.AUTHOR);
        return res;
    }

    /**
     * Content of the review.
     * Can be {@code null}.
     */
    @Nullable
    public String getContent() {
        String res = getStringOrNull(ReviewColumns.CONTENT);
        return res;
    }

    /**
     * Url to the review.
     * Can be {@code null}.
     */
    @Nullable
    public String getUrl() {
        String res = getStringOrNull(ReviewColumns.URL);
        return res;
    }

    /**
     * The id of the movie referenced by this review.
     */
    public long getMovieId() {
        Long res = getLongOrNull(ReviewColumns.MOVIE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'movie_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code external_id} value.
     */
    public long getMovieExternalId() {
        Long res = getLongOrNull(MovieColumns.EXTERNAL_ID);
        if (res == null)
            throw new NullPointerException("The value of 'external_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Title of the movie.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        return res;
    }

    /**
     * Overview of the movie.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        return res;
    }

    /**
     * Release date of the movie.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieReleaseDate() {
        String res = getStringOrNull(MovieColumns.RELEASE_DATE);
        return res;
    }

    /**
     * Avarage rating of the movie.
     * Can be {@code null}.
     */
    @Nullable
    public String getMovieVoteAvarage() {
        String res = getStringOrNull(MovieColumns.VOTE_AVARAGE);
        return res;
    }

    /**
     * Url path to the poster.
     * Can be {@code null}.
     */
    @Nullable
    public String getMoviePosterPath() {
        String res = getStringOrNull(MovieColumns.POSTER_PATH);
        return res;
    }
}
