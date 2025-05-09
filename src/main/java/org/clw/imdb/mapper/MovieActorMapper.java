package org.clw.imdb.mapper;

import org.clw.imdb.dto.actor.AddMovieActorDto;
import org.clw.imdb.dto.movie.MovieActorDto;
import org.clw.imdb.model.MovieActor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieActorMapper {
    MovieActorDto convert(MovieActor movieActor);

    MovieActor convert(MovieActorDto movieActorDto);
}
