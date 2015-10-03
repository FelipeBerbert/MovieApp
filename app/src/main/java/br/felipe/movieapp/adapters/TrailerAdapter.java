package br.felipe.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.felipe.movieapp.R;
import br.felipe.movieapp.interfaces.Connector;
import br.felipe.movieapp.models.Video;

/**
 * Created by Felipe Berbert on 01/10/2015.
 */
public class TrailerAdapter extends ArrayAdapter<Video> {

    Context context;
    int layoutId;



    public TrailerAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        TrailerHolder trailerHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layoutId, parent, false);
            trailerHolder = new TrailerHolder(view);
            view.setTag(trailerHolder);
        } else{
            trailerHolder = (TrailerHolder) view.getTag();
        }
        Video video = getItem(position);
        final String videoKey = video.getKey();
        trailerHolder.name.setText(video.getName());
        trailerHolder.thumb.setAdjustViewBounds(true);
        Picasso.with(context).load(String.format(Connector.YOUTUBE_THUMB_URL, video.getKey()))
                .into(trailerHolder.thumb);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Connector.YOUTUBE_VIDEO_URL + videoKey)));
            }
        });

        return view;
    }

    public static class TrailerHolder {
        public final TextView name;
        public final ImageView thumb;

        public TrailerHolder(View view) {
            name = (TextView) view.findViewById(R.id.video_name_text);
            thumb = (ImageView) view.findViewById((R.id.thumb_iv));

        }
    }
}
