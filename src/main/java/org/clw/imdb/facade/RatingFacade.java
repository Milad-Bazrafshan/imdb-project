package org.clw.imdb.facade;

import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.dto.filter.ImdbRatingFilterDto;
import org.clw.imdb.model.ImdbRating;

import java.util.List;

public interface RatingFacade {
    RatingDto createRating(RatingDto rating);
    List<RatingDto> getRating(ImdbRatingFilterDto filter);
    List<ImdbRating> getRatingList(ImdbRatingFilterDto filter);
    RatingDto getRating(long id);
}
