/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.video;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import br.felipe.movieapp.models.Video;
import br.felipe.movieapp.provider.base.AbstractCursor;
import br.felipe.movieapp.provider.movie.*;

/**
 * Cursor wrapper for the {@code video} table.
 */
public class VideoCursor extends AbstractCursor implements VideoModel {
    public VideoCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(VideoColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code video_id} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getVideoId() {
        String res = getStringOrNull(VideoColumns.VIDEO_ID);
        if (res == null)
            throw new NullPointerException("The value of 'video_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Name of the video.
     * Can be {@code null}.
     */
    @Nullable
    public String getName() {
        String res = getStringOrNull(VideoColumns.NAME);
        return res;
    }

    /**
     * Site that hosts the video.
     * Can be {@code null}.
     */
    @Nullable
    public String getSite() {
        String res = getStringOrNull(VideoColumns.SITE);
        return res;
    }

    /**
     * Type of video.
     * Can be {@code null}.
     */
    @Nullable
    public String getType() {
        String res = getStringOrNull(VideoColumns.TYPE);
        return res;
    }

    /**
     * Key to complete the video url.
     * Can be {@code null}.
     */
    @Nullable
    public String getKey() {
        String res = getStringOrNull(VideoColumns.KEY);
        return res;
    }

    public Video getVideo() {
        Video video = new Video();
        video.setId(getVideoId());
        video.setKey(getKey());
        //video.setMovieId(getMovieId());
        video.setName(getName());
        video.setSite(getSite());
        video.setType(getType());
        return video;
    }

    /**
     * The id of the movie referenced by this video.
     */
    public long getMovieId() {
        Long res = getLongOrNull(VideoColumns.MOVIE_ID);
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
