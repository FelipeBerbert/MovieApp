/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.video;

import br.felipe.movieapp.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Review
 */
public interface VideoModel extends BaseModel {

    /**
     * Name of the video.
     * Can be {@code null}.
     */
    @Nullable
    String getName();

    /**
     * Site that hosts the video.
     * Can be {@code null}.
     */
    @Nullable
    String getSite();

    /**
     * Type of video.
     * Can be {@code null}.
     */
    @Nullable
    String getType();

    /**
     * Key to complete the video url.
     * Can be {@code null}.
     */
    @Nullable
    String getKey();

    /**
     * The id of the movie referenced by this video.
     */
    long getMovieId();
}
