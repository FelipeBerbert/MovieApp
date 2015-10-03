package br.felipe.movieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.felipe.movieapp.R;
import br.felipe.movieapp.models.Review;

/**
 * Created by Felipe Berbert on 03/10/2015.
 */
public class ReviewAdapter extends ArrayAdapter<Review> {


        Context context;
        int layoutId;



        public ReviewAdapter(Context context, int resource) {
            super(context, resource);
            this.context = context;
            this.layoutId = resource;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View view = convertView;
            ReviewHolder reviewHolder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(layoutId, parent, false);
                reviewHolder = new ReviewHolder(view);
                view.setTag(reviewHolder);
            } else{
                reviewHolder = (ReviewHolder) view.getTag();
            }
            final Review review = getItem(position);

            reviewHolder.author.setText(review.getAuthor());
            reviewHolder.content.setText(review.getContent());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(review.getUrl())));
                }
            });

            return view;
        }

        public static class ReviewHolder {
            public final TextView author;
            public final TextView content;

            public ReviewHolder(View view) {
                author = (TextView) view.findViewById(R.id.author_text);
                content = (TextView) view.findViewById((R.id.content_text));

            }
        }
}
