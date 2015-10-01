package br.felipe.movieapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import br.felipe.movieapp.interfaces.Connector;

/**
 * Created by felip on 27/09/2015.
 */
public class Utils {

    public static boolean getWifi(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static String getMoviePosterUrl(Context context){
        if(getWifi(context))
            return Connector.BASE_POSTER_URL_MEDIUM;
        else
            return Connector.BASE_POSTER_URL_SMALL;
    }
}
