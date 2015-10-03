package br.felipe.movieapp.connection;

import br.felipe.movieapp.models.Video;

/**
 * Created by Felipe Berbert on 01/10/2015.
 */
public class VideoResponse {

    private Video[] results;

    public Video[] getResults() {
        return results;
    }

    public void setResults(Video[] results) {
        this.results = results;
    }
}
