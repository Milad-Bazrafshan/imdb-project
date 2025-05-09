package org.clw.imdb.facade;

import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.actor.ActorsInfoFilterDto;
import org.clw.imdb.dto.actor.AddMovieActorDto;
import org.clw.imdb.dto.movie.*;

import java.util.List;

public interface ActorsFacade {
    AddMovieActorDto createMovieActor(AddMovieActorDto addMovieActorDto);

    TypeDto createActorType(TypeDto typeDto);

    ActorsInfoDto createActorsInfo(ActorsInfoDto actorsInfoDto);

    void createGroupActorsInfo(List<ActorsInfoDto> actors);

    List<ActorsInfoDto> getActors(ActorsInfoFilterDto filter);

    List<MovieBasicInfoDto> getMoviesByActor(MovieFilterDto filter);

    List<MovieBasicInfoDto> getMovieByCommonFactors(String fromActorTypeCode, String toActorTypeCode);
}
