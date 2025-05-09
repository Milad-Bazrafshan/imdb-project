package org.clw.imdb.mapper;

import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.rate.MovieRatingDto;
import org.clw.imdb.model.ActorsInfo;
import org.clw.imdb.model.MovieRating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieRatingMapper {
    MovieRatingDto convert(MovieRating movieRating);

    MovieRating convert(MovieRatingDto movieRatingDto);
}
