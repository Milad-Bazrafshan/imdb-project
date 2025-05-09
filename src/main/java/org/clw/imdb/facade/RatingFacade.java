package org.clw.imdb.facade;

import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.dto.filter.ImdbRatingFilterDto;
import org.clw.imdb.dto.rate.MovieRatingDto;
import org.clw.imdb.model.MovieRating;

import java.util.List;

public interface RatingFacade {
    MovieRatingDto createRating(MovieRatingDto rating);
    MovieRatingDto getMovieRate(Long movieId);
    RatingDto getRating(long id);
}
