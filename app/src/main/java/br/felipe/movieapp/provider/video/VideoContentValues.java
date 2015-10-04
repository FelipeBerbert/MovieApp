/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.video;

import java.util.Date;

import android.content.Context;
import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.felipe.movieapp.models.Video;
import br.felipe.movieapp.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code video} table.
 */
public class VideoContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return VideoColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable VideoSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(Context context, @Nullable VideoSelection where) {
        return context.getContentResolver().update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public VideoContentValues putVideoId(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("videoId must not be null");
        mContentValues.put(VideoColumns.VIDEO_ID, value);
        return this;
    }


    /**
     * Name of the video.
     */
    public VideoContentValues putName(@Nullable String value) {
        mContentValues.put(VideoColumns.NAME, value);
        return this;
    }

    public VideoContentValues putNameNull() {
        mContentValues.putNull(VideoColumns.NAME);
        return this;
    }

    /**
     * Site that hosts the video.
     */
    public VideoContentValues putSite(@Nullable String value) {
        mContentValues.put(VideoColumns.SITE, value);
        return this;
    }

    public VideoContentValues putSiteNull() {
        mContentValues.putNull(VideoColumns.SITE);
        return this;
    }

    /**
     * Type of video.
     */
    public VideoContentValues putType(@Nullable String value) {
        mContentValues.put(VideoColumns.TYPE, value);
        return this;
    }

    public VideoContentValues putTypeNull() {
        mContentValues.putNull(VideoColumns.TYPE);
        return this;
    }

    /**
     * Key to complete the video url.
     */
    public VideoContentValues putKey(@Nullable String value) {
        mContentValues.put(VideoColumns.KEY, value);
        return this;
    }

    public VideoContentValues putKeyNull() {
        mContentValues.putNull(VideoColumns.KEY);
        return this;
    }

    /**
     * The id of the movie referenced by this video.
     */
    public VideoContentValues putMovieId(long value) {
        mContentValues.put(VideoColumns.MOVIE_ID, value);
        return this;
    }

    public VideoContentValues putVideo(Video value, long movieId) {
        mContentValues.put(VideoColumns.VIDEO_ID, value.getId());
        mContentValues.put(VideoColumns.MOVIE_ID, movieId);
        mContentValues.put(VideoColumns.KEY, value.getKey());
        mContentValues.put(VideoColumns.NAME, value.getName());
        mContentValues.put(VideoColumns.SITE, value.getSite());
        mContentValues.put(VideoColumns.TYPE, value.getType());
        return this;
    }
}
