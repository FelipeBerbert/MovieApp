package br.felipe.movieapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import br.felipe.movieapp.Movie;
import br.felipe.movieapp.R;
import br.felipe.movieapp.interfaces.Fetcher;

/**
 * Created by Felipe on 05/08/2015.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    Context context;


    public MovieAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ImageView view = (ImageView) convertView;
        if (view == null) {
            view = new ImageView(context);
            view.setAdjustViewBounds(true);
        }else{
            view = (ImageView) convertView;
        }
        String url = getItem(position).getPosterUrl();

        Picasso.with(context).load(Fetcher.BASE_POSTER_URL+url).placeholder(context.getResources().getDrawable(R.mipmap.loading)).error(context.getResources().getDrawable(R.mipmap.error)).into(view);

        return view;
    }
}
