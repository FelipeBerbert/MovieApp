/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.movie;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.felipe.movieapp.models.Movie;
import br.felipe.movieapp.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code movie} table.
 */
public class MovieContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return MovieColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable MovieSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable MovieSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public MovieContentValues putExternalId(long value) {
        mContentValues.put(MovieColumns.EXTERNAL_ID, value);
        return this;
    }


    /**
     * Title of the movie.
     */
    public MovieContentValues putTitle(@Nullable String value) {
        mContentValues.put(MovieColumns.TITLE, value);
        return this;
    }

    public MovieContentValues putTitleNull() {
        mContentValues.putNull(MovieColumns.TITLE);
        return this;
    }

    /**
     * Overview of the movie.
     */
    public MovieContentValues putOverview(@Nullable String value) {
        mContentValues.put(MovieColumns.OVERVIEW, value);
        return this;
    }

    public MovieContentValues putOverviewNull() {
        mContentValues.putNull(MovieColumns.OVERVIEW);
        return this;
    }

    /**
     * Release date of the movie.
     */
    public MovieContentValues putReleaseDate(@Nullable String value) {
        mContentValues.put(MovieColumns.RELEASE_DATE, value);
        return this;
    }

    public MovieContentValues putReleaseDateNull() {
        mContentValues.putNull(MovieColumns.RELEASE_DATE);
        return this;
    }

    /**
     * Avarage rating of the movie.
     */
    public MovieContentValues putVoteAvarage(@Nullable String value) {
        mContentValues.put(MovieColumns.VOTE_AVARAGE, value);
        return this;
    }

    public MovieContentValues putVoteAvarageNull() {
        mContentValues.putNull(MovieColumns.VOTE_AVARAGE);
        return this;
    }

    /**
     * Url path to the poster.
     */
    public MovieContentValues putPosterPath(@Nullable String value) {
        mContentValues.put(MovieColumns.POSTER_PATH, value);
        return this;
    }

    public MovieContentValues putPosterPathNull() {
        mContentValues.putNull(MovieColumns.POSTER_PATH);
        return this;
    }

    public MovieContentValues putMovie(Movie movie) {
        mContentValues.put(MovieColumns.EXTERNAL_ID, movie.getId());
        mContentValues.put(MovieColumns.TITLE, movie.getTitle());
        mContentValues.put(MovieColumns.OVERVIEW, movie.getOverview());
        mContentValues.put(MovieColumns.RELEASE_DATE, movie.getRelease_date());
        mContentValues.put(MovieColumns.VOTE_AVARAGE, movie.getVote_average());
        mContentValues.put(MovieColumns.POSTER_PATH, movie.getPoster_path());
        return this;
    }
}
