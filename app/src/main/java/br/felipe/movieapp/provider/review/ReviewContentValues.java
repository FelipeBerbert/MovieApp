/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.review;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.felipe.movieapp.models.Review;
import br.felipe.movieapp.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code review} table.
 */
public class ReviewContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ReviewColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable ReviewSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable ReviewSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ReviewContentValues putReviewId(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("reviewId must not be null");
        mContentValues.put(ReviewColumns.REVIEW_ID, value);
        return this;
    }


    /**
     * Author of the review.
     */
    public ReviewContentValues putAuthor(@Nullable String value) {
        mContentValues.put(ReviewColumns.AUTHOR, value);
        return this;
    }

    public ReviewContentValues putAuthorNull() {
        mContentValues.putNull(ReviewColumns.AUTHOR);
        return this;
    }

    /**
     * Content of the review.
     */
    public ReviewContentValues putContent(@Nullable String value) {
        mContentValues.put(ReviewColumns.CONTENT, value);
        return this;
    }

    public ReviewContentValues putContentNull() {
        mContentValues.putNull(ReviewColumns.CONTENT);
        return this;
    }

    /**
     * Url to the review.
     */
    public ReviewContentValues putUrl(@Nullable String value) {
        mContentValues.put(ReviewColumns.URL, value);
        return this;
    }

    public ReviewContentValues putUrlNull() {
        mContentValues.putNull(ReviewColumns.URL);
        return this;
    }

    /**
     * The id of the movie referenced by this review.
     */
    public ReviewContentValues putMovieId(long value) {
        mContentValues.put(ReviewColumns.MOVIE_ID, value);
        return this;
    }

    public ReviewContentValues putReview(Review value) {
        mContentValues.put(ReviewColumns.REVIEW_ID, value.getId());
        mContentValues.put(ReviewColumns.MOVIE_ID, value.getMovieId());
        mContentValues.put(ReviewColumns.AUTHOR, value.getAuthor());
        mContentValues.put(ReviewColumns.CONTENT, value.getContent());
        mContentValues.put(ReviewColumns.URL, value.getUrl());
        return this;
    }

}
