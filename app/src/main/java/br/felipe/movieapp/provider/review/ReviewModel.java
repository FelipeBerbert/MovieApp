/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.review;

import br.felipe.movieapp.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Review
 */
public interface ReviewModel extends BaseModel {

    /**
     * Get the {@code review_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getReviewId();

    /**
     * Author of the review.
     * Can be {@code null}.
     */
    @Nullable
    String getAuthor();

    /**
     * Content of the review.
     * Can be {@code null}.
     */
    @Nullable
    String getContent();

    /**
     * Url to the review.
     * Can be {@code null}.
     */
    @Nullable
    String getUrl();

    /**
     * The id of the movie referenced by this review.
     */
    long getMovieId();
}
