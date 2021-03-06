/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.review;

import android.net.Uri;
import android.provider.BaseColumns;

import br.felipe.movieapp.provider.MovieProvider;
import br.felipe.movieapp.provider.movie.MovieColumns;
import br.felipe.movieapp.provider.review.ReviewColumns;
import br.felipe.movieapp.provider.video.VideoColumns;

/**
 * Review
 */
public class ReviewColumns implements BaseColumns {
    public static final String TABLE_NAME = "review";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String REVIEW_ID = "review_id";

    /**
     * Author of the review.
     */
    public static final String AUTHOR = "author";

    /**
     * Content of the review.
     */
    public static final String CONTENT = "content";

    /**
     * Url to the review.
     */
    public static final String URL = "url";

    /**
     * The id of the movie referenced by this review.
     */
    public static final String MOVIE_ID = "movie_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            REVIEW_ID,
            AUTHOR,
            CONTENT,
            URL,
            MOVIE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(REVIEW_ID) || c.contains("." + REVIEW_ID)) return true;
            if (c.equals(AUTHOR) || c.contains("." + AUTHOR)) return true;
            if (c.equals(CONTENT) || c.contains("." + CONTENT)) return true;
            if (c.equals(URL) || c.contains("." + URL)) return true;
            if (c.equals(MOVIE_ID) || c.contains("." + MOVIE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_MOVIE = TABLE_NAME + "__" + MovieColumns.TABLE_NAME;
}
