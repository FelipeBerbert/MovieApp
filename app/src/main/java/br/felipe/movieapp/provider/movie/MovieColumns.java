/**
*Classes generated for the MovieApp, for the Udacity Android Nanodegree, using the "Android ContentProvider Generator" (https://github.com/BoD/android-contentprovider-generator)
*/
package br.felipe.movieapp.provider.movie;

import android.net.Uri;
import android.provider.BaseColumns;

import br.felipe.movieapp.provider.MovieProvider;
import br.felipe.movieapp.provider.movie.MovieColumns;
import br.felipe.movieapp.provider.review.ReviewColumns;
import br.felipe.movieapp.provider.video.VideoColumns;

/**
 * Movie
 */
public class MovieColumns implements BaseColumns {
    public static final String TABLE_NAME = "movie";
    public static final Uri CONTENT_URI = Uri.parse(MovieProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String EXTERNAL_ID = "external_id";

    /**
     * Title of the movie.
     */
    public static final String TITLE = "title";

    /**
     * Overview of the movie.
     */
    public static final String OVERVIEW = "overview";

    /**
     * Release date of the movie.
     */
    public static final String RELEASE_DATE = "release_date";

    /**
     * Avarage rating of the movie.
     */
    public static final String VOTE_AVARAGE = "vote_avarage";

    /**
     * Url path to the poster.
     */
    public static final String POSTER_PATH = "poster_path";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            EXTERNAL_ID,
            TITLE,
            OVERVIEW,
            RELEASE_DATE,
            VOTE_AVARAGE,
            POSTER_PATH
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(EXTERNAL_ID) || c.contains("." + EXTERNAL_ID)) return true;
            if (c.equals(TITLE) || c.contains("." + TITLE)) return true;
            if (c.equals(OVERVIEW) || c.contains("." + OVERVIEW)) return true;
            if (c.equals(RELEASE_DATE) || c.contains("." + RELEASE_DATE)) return true;
            if (c.equals(VOTE_AVARAGE) || c.contains("." + VOTE_AVARAGE)) return true;
            if (c.equals(POSTER_PATH) || c.contains("." + POSTER_PATH)) return true;
        }
        return false;
    }

}
