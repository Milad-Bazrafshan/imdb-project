package org.clw.imdb.facade;

import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.actor.ActorsInfoFilterDto;
import org.clw.imdb.dto.actor.MovieActorDto;
import org.clw.imdb.dto.movie.MovieFeatureTypeDto;
import org.clw.imdb.dto.movie.MovieFilterDto;
import org.clw.imdb.dto.movie.MovieGenreDto;
import org.clw.imdb.dto.movie.TypeDto;
import org.clw.imdb.model.MovieBasicInfo;

import java.util.List;

public interface ActorsFacade {
    MovieActorDto createMovieActor(MovieActorDto movieActorDto);

    TypeDto createActorType(TypeDto typeDto);

    ActorsInfoDto createActorsInfo(ActorsInfoDto actorsInfoDto);

    void createGroupActorsInfo(List<ActorsInfoDto> actors);

    List<ActorsInfoDto> getActors(ActorsInfoFilterDto filter);

    List<MovieBasicInfo> getMoviesByActor(MovieFilterDto filter);
}
