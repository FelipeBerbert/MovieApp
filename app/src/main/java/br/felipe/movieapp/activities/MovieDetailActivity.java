package br.felipe.movieapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.felipe.movieapp.MovieGridFragment;
import br.felipe.movieapp.R;
import br.felipe.movieapp.fragments.MovieDetailFragment;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if(savedInstanceState == null){
            Bundle args = new Bundle();
            args.putParcelable(MovieDetailFragment.MOVIE,getIntent().getParcelableExtra(getString(R.string.app_package)+".MovieObject"));
            MovieDetailFragment df = new MovieDetailFragment();
            df.setArguments(args);
            getSupportFragmentManager().beginTransaction().add(R.id.frag_movie_detail, df).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
