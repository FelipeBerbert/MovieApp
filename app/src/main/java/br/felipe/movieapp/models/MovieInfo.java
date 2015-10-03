package br.felipe.movieapp.models;

import br.felipe.movieapp.connection.ReviewResponse;
import br.felipe.movieapp.connection.VideoResponse;

/**
 * Created by Felipe Berbert on 01/10/2015.
 */
public class MovieInfo {
    private Review[] reviewResponse;
    private Video[] videoResponse;

    public MovieInfo(Review[] reviewResponse, Video[] videoResponse) {
        this.reviewResponse = reviewResponse;
        this.videoResponse = videoResponse;
    }

    public Review[] getReviewResponse() {
        return reviewResponse;
    }

    public void setReviewResponse(Review[] reviewResponse) {
        this.reviewResponse = reviewResponse;
    }

    public Video[] getVideoResponse() {
        return videoResponse;
    }

    public void setVideoResponse(Video[] videoResponse) {
        this.videoResponse = videoResponse;
    }
}
