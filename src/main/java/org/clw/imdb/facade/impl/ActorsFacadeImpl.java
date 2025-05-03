package org.clw.imdb.facade.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.actor.ActorsInfoFilterDto;
import org.clw.imdb.dto.actor.MovieActorDto;
import org.clw.imdb.dto.movie.MovieFilterDto;
import org.clw.imdb.dto.movie.TypeDto;
import org.clw.imdb.exception.ActorNotFoundException;
import org.clw.imdb.exception.ActorTypeException;
import org.clw.imdb.exception.MovieNotFoundException;
import org.clw.imdb.facade.ActorsFacade;
import org.clw.imdb.mapper.ActorsInfoMapper;
import org.clw.imdb.mapper.ActorsTypeMapper;
import org.clw.imdb.model.ActorType;
import org.clw.imdb.model.ActorsInfo;
import org.clw.imdb.model.MovieActor;
import org.clw.imdb.model.MovieBasicInfo;
import org.clw.imdb.services.ActorsService;
import org.clw.imdb.services.MovieService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ActorsFacadeImpl implements ActorsFacade {
    private final ActorsService actorsService;
    private final ActorsInfoMapper actorsInfoMapper;
    private final MovieService movieService;
    private final ActorsTypeMapper actorsTypeMapper;

    @Override
    public MovieActorDto createMovieActor(MovieActorDto movieActorDto) {
        ActorType actorType = actorsService.getActorType(movieActorDto.getTypeCode());
        if (ObjectUtils.isEmpty(actorType)) throw new ActorTypeException();

        MovieBasicInfo movieBasicInfo = movieService.getMovieBasicInfo(movieActorDto.getMovieId());
        if (ObjectUtils.isEmpty(movieBasicInfo)) throw new MovieNotFoundException();

        ActorsInfo actorsInfo = actorsService.getActorsInfo(movieActorDto.getActorId());
        if (ObjectUtils.isEmpty(actorsInfo)) throw new ActorNotFoundException();

        MovieActor movieActor = new MovieActor();
        movieActor.setType(actorType);
        movieActor.setActorInfo(actorsInfo);
        actorsService.createMovieActor(movieActor);
        return movieActorDto;
    }

    @Override
    public TypeDto createActorType(TypeDto typeDto) {
        actorsService.createActorType(actorsTypeMapper.convert(typeDto));
        return typeDto;
    }

    @Override
    public ActorsInfoDto createActorsInfo(ActorsInfoDto actorsInfoDto) {
        actorsService.createActorsInfo(actorsInfoMapper.convert(actorsInfoDto));
        return actorsInfoDto;
    }

    @Override
    public void createGroupActorsInfo(List<ActorsInfoDto> actors) {
        for (ActorsInfoDto dto : actors) {
            ActorsInfo actorsInfo = actorsInfoMapper.convert(dto);
            actorsService.createActorsInfo(actorsInfo);
        }
    }

    @Override
    public List<ActorsInfoDto> getActors(ActorsInfoFilterDto filter) {
        if (ObjectUtils.isEmpty(filter.getFrom()) || ObjectUtils.isEmpty(filter.getSize()))
            throw new IllegalArgumentException("from and size in required");

        return actorsService.getActors(filter).stream().map(actorsInfoMapper::convert).toList();
    }

    @Override
    public List<MovieBasicInfo> getMoviesByActor(MovieFilterDto filter) {
        /*if (ObjectUtils.isEmpty(filter.getFrom()) || ObjectUtils.isEmpty(filter.getSize()))
            throw new IllegalArgumentException("from and size in required");*/

        return actorsService.getMoviesByActor(filter);
    }
}
