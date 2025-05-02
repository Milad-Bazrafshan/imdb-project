package org.clw.imdb.facade;

import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.actor.ActorsInfoFilterDto;
import org.clw.imdb.dto.actor.MovieActorDto;
import org.clw.imdb.dto.movie.MovieFeatureTypeDto;
import org.clw.imdb.dto.movie.MovieGenreDto;

import java.util.List;

public interface ActorsFacade {
    MovieActorDto createMovieActor(MovieActorDto movieActorDto);
    ActorsInfoDto createActorsInfo(ActorsInfoDto actorsInfoDto);
    void createGroupActorsInfo(List<ActorsInfoDto> actors);
    List<ActorsInfoDto> getActors(ActorsInfoFilterDto filter);
}
