/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.movie;

import br.felipe.movieapp.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Movie
 */
public interface MovieModel extends BaseModel {

    /**
     * Get the {@code external_id} value.
     */
    long getExternalId();

    /**
     * Title of the movie.
     * Can be {@code null}.
     */
    @Nullable
    String getTitle();

    /**
     * Overview of the movie.
     * Can be {@code null}.
     */
    @Nullable
    String getOverview();

    /**
     * Release date of the movie.
     * Can be {@code null}.
     */
    @Nullable
    String getReleaseDate();

    /**
     * Avarage rating of the movie.
     * Can be {@code null}.
     */
    @Nullable
    String getVoteAvarage();

    /**
     * Url path to the poster.
     * Can be {@code null}.
     */
    @Nullable
    String getPosterPath();
}
