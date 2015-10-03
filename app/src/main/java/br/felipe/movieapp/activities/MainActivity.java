package br.felipe.movieapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.felipe.movieapp.models.Movie;
import br.felipe.movieapp.fragments.MovieGridFragment;
import br.felipe.movieapp.R;
import br.felipe.movieapp.fragments.MovieDetailFragment;

public class MainActivity extends AppCompatActivity implements MovieGridFragment.Callback {

    private static final String MOVIEDETAILFRAGMENT_TAG = "MOVIEDETAILFRAGTAG";

    boolean isTabletLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.frag_movie_detail) != null) {
            isTabletLayout = true;

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frag_movie_detail, new MovieDetailFragment(), MOVIEDETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            isTabletLayout = false;
        }
        MovieGridFragment movieGridFragment = (MovieGridFragment) getSupportFragmentManager().findFragmentById(R.id.frag_movie_grid);
        movieGridFragment.setIsTabletLayout(isTabletLayout);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(Movie movie) {
        if (isTabletLayout) {
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            Bundle args = new Bundle();
            args.putParcelable(MovieDetailFragment.MOVIE, movie);
            movieDetailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_movie_detail, movieDetailFragment, MOVIEDETAILFRAGMENT_TAG).commit();
        } else {
            Intent intent = new Intent(this, MovieDetailActivity.class).putExtra(getString(R.string.app_package) + ".MovieObject", movie);
            startActivity(intent);
        }
    }
}
