package br.felipe.movieapp.MovieList;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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

import java.util.List;

import br.felipe.movieapp.models.Movie;
import br.felipe.movieapp.R;
import br.felipe.movieapp.adapters.MovieAdapter;
import br.felipe.movieapp.connection.FetchMovie;
import br.felipe.movieapp.connection.MovieResponse;
import br.felipe.movieapp.interfaces.Connector;
import br.felipe.movieapp.provider.movie.MovieCursor;
import br.felipe.movieapp.provider.movie.MovieSelection;


public class MovieGridFragment extends Fragment implements MovieListContract.View {

    static final String PREF_ORDER = "order";
    static final String PARAMETER_VALUE_FAVORITES = "favorite";

    GridView filmGrid;
    MovieAdapter adapter;
    TextView noDataText;
    SwipeRefreshLayout srl;
    boolean isTabletLayout;
    private MovieListContract.Presenter presenter;

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
        srl = (SwipeRefreshLayout) rootView.findViewById(R.id.srl);
        adapter = new MovieAdapter(getActivity(), R.layout.movie_item_view);
        srl.setEnabled(false);
        srl.setRefreshing(false);
        filmGrid.setAdapter(adapter);
        filmGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = adapter.getItem(i);
                if (movie != null)
                    ((Callback) getActivity()).onItemSelected(movie);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    /*  private void fetchMovies() {
        String order = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(PREF_ORDER, FetchMovie.PARAMETER_VALUE_POP);
        if(order.equals(PARAMETER_VALUE_FAVORITES)){
            fetchFavorites();
        }else {
            checkConnection();
            adapter.clear();
            FetchMovie fm = new FetchMovie(this, order);
            fm.execute();
        }
    }*/

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

/*    private void fetchFavorites(){
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
    }*/

  /*  @Override
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

    }*/
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
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(PREF_ORDER, FetchMovie.PARAMETER_VALUE_POP).apply();
            presenter.loadMovieList(FetchMovie.PARAMETER_VALUE_POP);
            return true;
        }
        if (id == R.id.action_order_rating){
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(PREF_ORDER, FetchMovie.PARAMETER_VALUE_RATING).apply();
            presenter.loadMovieList(FetchMovie.PARAMETER_VALUE_RATING);
            return true;
        }
        if (id == R.id.action_order_favorites){
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(PREF_ORDER, PARAMETER_VALUE_FAVORITES).apply();
            presenter.loadFavoriteMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setIsTabletLayout(boolean isTabletLayout) {
        this.isTabletLayout = isTabletLayout;
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        srl.setRefreshing(isLoading);
    }

    @Override
    public void showMovieList(List<Movie> movies) {
        noDataText.setVisibility(View.GONE);
        filmGrid.setVisibility(View.VISIBLE);
        adapter.clear();
        adapter.addAll(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNoMovies() {
        noDataText.setVisibility(View.VISIBLE);
        filmGrid.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(MovieListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public interface Callback {
        public void onItemSelected(Movie movie);
    }

}
