package br.felipe.movieapp.connection;

import br.felipe.movieapp.models.Review;

/**
 * Created by Felipe Berbert on 01/10/2015.
 */
public class ReviewResponse {
    private Review[] results;

    public Review[] getResults() {
        return results;
    }

    public void setResults(Review[] results) {
        this.results = results;
    }
}
