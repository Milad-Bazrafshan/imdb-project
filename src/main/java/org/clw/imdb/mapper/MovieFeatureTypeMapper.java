package org.clw.imdb.mapper;

import org.clw.imdb.dto.movie.MovieFeatureTypeDto;
import org.clw.imdb.dto.movie.MovieGenreDto;
import org.clw.imdb.model.MovieFeatureType;
import org.clw.imdb.model.MovieGenre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieFeatureTypeMapper {
    MovieFeatureTypeDto convert(MovieFeatureType movieFeatureType);

    MovieFeatureType convert(MovieFeatureTypeDto movieFeatureTypeDto);
}
