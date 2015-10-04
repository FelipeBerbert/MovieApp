/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.movie;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.felipe.movieapp.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code movie} table.
 */
public class MovieCursor extends AbstractCursor implements MovieModel {
    public MovieCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(MovieColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code external_id} value.
     */
    public long getExternalId() {
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
    public String getTitle() {
        String res = getStringOrNull(MovieColumns.TITLE);
        return res;
    }

    /**
     * Overview of the movie.
     * Can be {@code null}.
     */
    @Nullable
    public String getOverview() {
        String res = getStringOrNull(MovieColumns.OVERVIEW);
        return res;
    }

    /**
     * Release date of the movie.
     * Can be {@code null}.
     */
    @Nullable
    public String getReleaseDate() {
        String res = getStringOrNull(MovieColumns.RELEASE_DATE);
        return res;
    }

    /**
     * Avarage rating of the movie.
     * Can be {@code null}.
     */
    @Nullable
    public String getVoteAvarage() {
        String res = getStringOrNull(MovieColumns.VOTE_AVARAGE);
        return res;
    }

    /**
     * Url path to the poster.
     * Can be {@code null}.
     */
    @Nullable
    public String getPosterPath() {
        String res = getStringOrNull(MovieColumns.POSTER_PATH);
        return res;
    }
}
