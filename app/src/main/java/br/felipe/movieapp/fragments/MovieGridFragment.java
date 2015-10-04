package br.felipe.movieapp.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import br.felipe.movieapp.models.Movie;
import br.felipe.movieapp.R;
import br.felipe.movieapp.adapters.MovieAdapter;
import br.felipe.movieapp.connection.FetchMovie;
import br.felipe.movieapp.connection.MovieResponse;
import br.felipe.movieapp.interfaces.Connector;
import br.felipe.movieapp.provider.movie.MovieCursor;
import br.felipe.movieapp.provider.movie.MovieSelection;


public class MovieGridFragment extends Fragment implements Connector {

    static final String PREF_ORDER = "order";
    static final String PARAMETER_VALUE_FAVORITES = "favorite";

    GridView filmGrid;
    MovieAdapter adapter;
    TextView noDataText;
    boolean isTabletLayout;

    public MovieGridFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


        filmGrid = (GridView) rootView.findViewById(R.id.film_grid);
        noDataText = (TextView) rootView.findViewById(R.id.no_data_text);
        adapter = new MovieAdapter(getActivity(), R.layout.movie_item_view);
        filmGrid.setAdapter(adapter);
        filmGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = adapter.getItem(i);
                if (movie != null)
                    ((Callback) getActivity()).onItemSelected(movie);
                //Intent intent = new Intent(getActivity(), MovieDetailActivity.class).putExtra(getString(R.string.app_package) + ".MovieObject", adapter.getItem(i));
                //startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        fetchMovies();
    }

    private void fetchMovies() {
        String order = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(PREF_ORDER, FetchMovie.PARAMETER_VALUE_POP);
        if(order.equals(PARAMETER_VALUE_FAVORITES)){
            fetchFavorites();
        }else {
            checkConnection();
            adapter.clear();
            FetchMovie fm = new FetchMovie();
            fm.setConnector(this);
            fm.setOrder(order);
            fm.execute();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfiguration) {
        super.onConfigurationChanged(newConfiguration);
        if(!isTabletLayout) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                filmGrid.setNumColumns(5);
            else
                filmGrid.setNumColumns(3);
        }
    }

    private void fetchFavorites(){
        MovieSelection where = new MovieSelection();
        MovieCursor cursor = where.query(getActivity().getContentResolver());
        adapter.clear();
        if(cursor.moveToFirst()) {
            filmGrid.setVisibility(View.VISIBLE);
            noDataText.setVisibility(View.GONE);
            adapter.add(cursor.getMovie());
            while (cursor.moveToNext()) {
                adapter.add(cursor.getMovie());
            }
        }
        cursor.close();
    }

    @Override
    public void onConnectionResult(Object movieList) {
        if (movieList != null && ((MovieResponse)movieList).results.length >  0) {
            filmGrid.setVisibility(View.VISIBLE);
            noDataText.setVisibility(View.GONE);
            for (Movie movie : ((MovieResponse)movieList).results) {
                adapter.add(movie);
            }
        }else{
            checkConnection();
        }

    }
    void checkConnection(){
        ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnected()) {
            connectionError();
        }
    }
    void connectionError(){
        filmGrid.setVisibility(View.GONE);
        noDataText.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main_fragment, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_order_popularity) {
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(PREF_ORDER, FetchMovie.PARAMETER_VALUE_POP).commit();
            fetchMovies();
            return true;
        }
        if (id == R.id.action_order_rating){
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(PREF_ORDER, FetchMovie.PARAMETER_VALUE_RATING).commit();
            fetchMovies();
            return true;
        }
        if (id == R.id.action_order_favorites){
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(PREF_ORDER, PARAMETER_VALUE_FAVORITES).commit();
            fetchMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setIsTabletLayout(boolean isTabletLayout) {
        this.isTabletLayout = isTabletLayout;
    }

    public interface Callback {
        public void onItemSelected(Movie movie);
    }

}
