/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.video;

import android.net.Uri;
import android.provider.BaseColumns;

import br.felipe.movieapp.provider.MovieProvider;
import br.felipe.movieapp.provider.movie.MovieColumns;
import br.felipe.movieapp.provider.review.ReviewColumns;
import br.felipe.movieapp.provider.video.VideoColumns;

/**
 * Video
 */
public class VideoColumns implements BaseColumns {
    public static final String TABLE_NAME = "video";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String VIDEO_ID = "video_id";

    /**
     * Name of the video.
     */
    public static final String NAME = "name";

    /**
     * Site that hosts the video.
     */
    public static final String SITE = "site";

    /**
     * Type of video.
     */
    public static final String TYPE = "type";

    /**
     * Key to complete the video url.
     */
    public static final String KEY = "key";

    /**
     * The id of the movie referenced by this video.
     */
    public static final String MOVIE_ID = "movie_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            VIDEO_ID,
            NAME,
            SITE,
            TYPE,
            KEY,
            MOVIE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(VIDEO_ID) || c.contains("." + VIDEO_ID)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(SITE) || c.contains("." + SITE)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(KEY) || c.contains("." + KEY)) return true;
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
