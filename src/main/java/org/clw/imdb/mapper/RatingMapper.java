package org.clw.imdb.mapper;

import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.model.MovieRating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    RatingDto convert(MovieRating movieRating);

    MovieRating convert(RatingDto ratingDto);
}
