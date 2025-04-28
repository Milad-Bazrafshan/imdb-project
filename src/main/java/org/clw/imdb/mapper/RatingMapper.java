package org.clw.imdb.mapper;

import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.model.ImdbRating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    RatingDto convert(ImdbRating imdbRating);

    ImdbRating convert(RatingDto ratingDto);
}
