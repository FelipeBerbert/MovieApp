package br.felipe.movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import br.felipe.movieapp.models.Movie;
import br.felipe.movieapp.R;
import br.felipe.movieapp.utils.Utils;

/**
 * Created by Felipe on 05/08/2015.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {

    Context context;
    int layoutId;

    public MovieAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        layoutId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ImageView view = (ImageView)convertView;
        if (view == null) {
            view = (ImageView) LayoutInflater.from(context).inflate(layoutId, parent, false);
            //view = new ImageView(context);
            //view.setAdjustViewBounds(true);
        }//else {
            //view = convertView;
        //}
        String url = getItem(position).getPoster_path();
        String posterURL = Utils.getMoviePosterUrl(context);
        Picasso.with(context).load(posterURL + url).placeholder(context.getResources().getDrawable(R.mipmap.loading)).error(context.getResources().getDrawable(R.mipmap.error)).into(view);

        return view;
    }
}
