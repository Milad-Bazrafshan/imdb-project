package org.clw.imdb.mapper;

import org.clw.imdb.dto.movie.MovieGenreDto;
import org.clw.imdb.model.MovieGenre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieGenreMapper {
    MovieGenreDto convert(MovieGenre movieGenre);

    MovieGenre convert(MovieGenreDto movieGenreDto);
}
