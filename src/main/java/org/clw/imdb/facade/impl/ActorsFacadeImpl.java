package org.clw.imdb.facade.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.actor.ActorsInfoFilterDto;
import org.clw.imdb.dto.actor.AddMovieActorDto;
import org.clw.imdb.dto.movie.MovieActorDto;
import org.clw.imdb.dto.movie.MovieBasicInfoDto;
import org.clw.imdb.dto.movie.MovieFilterDto;
import org.clw.imdb.dto.movie.TypeDto;
import org.clw.imdb.exception.ActorNotFoundException;
import org.clw.imdb.exception.ActorTypeException;
import org.clw.imdb.exception.MovieNotFoundException;
import org.clw.imdb.facade.ActorsFacade;
import org.clw.imdb.mapper.*;
import org.clw.imdb.model.ActorType;
import org.clw.imdb.model.ActorsInfo;
import org.clw.imdb.model.MovieActor;
import org.clw.imdb.model.MovieBasicInfo;
import org.clw.imdb.services.ActorsService;
import org.clw.imdb.services.MovieService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ActorsFacadeImpl implements ActorsFacade {
    private final ActorsService actorsService;
    private final ActorsInfoMapper actorsInfoMapper;
    private final MovieService movieService;
    private final ActorsTypeMapper actorsTypeMapper;
    private final MovieBasicInfoMapper movieBasicInfoMapper;
    private final MovieActorMapper movieActorMapper;

    @Override
    public AddMovieActorDto createMovieActor(AddMovieActorDto addMovieActorDto) {
        ActorType actorType = actorsService.getActorType(addMovieActorDto.getTypeCode());
        if (ObjectUtils.isEmpty(actorType)) throw new ActorTypeException();

        MovieBasicInfo movieBasicInfo = movieService.getMovieBasicInfo(addMovieActorDto.getMovieId());
        if (ObjectUtils.isEmpty(movieBasicInfo)) throw new MovieNotFoundException();

        ActorsInfo actorsInfo = actorsService.getActorsInfo(addMovieActorDto.getActorId());
        if (ObjectUtils.isEmpty(actorsInfo)) throw new ActorNotFoundException();

        MovieActor movieActor = new MovieActor();
        movieActor.setType(actorType);
        movieActor.setActorInfo(actorsInfo);
        actorsService.createMovieActor(movieActor);
        return addMovieActorDto;
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
    public List<MovieBasicInfoDto> getMoviesByActor(MovieFilterDto filter) {
        List<MovieBasicInfoDto> finalList = new ArrayList<>();
        actorsService.getMoviesByActor(filter).forEach(item -> {
            MovieBasicInfoDto dto = movieBasicInfoMapper.convert(item);
            dto.setActors(new ArrayList<>());
            item.getActors().forEach(actor -> {
                MovieActorDto movieActorDto = new MovieActorDto();
                movieActorDto.setName(actor.getActorInfo().getFirstName() + " " + actor.getActorInfo().getLastName());
                movieActorDto.setRate(actor.getActorInfo().getRate());
                movieActorDto.setHeight(actor.getActorInfo().getHeight());
                movieActorDto.setAlive(actor.getActorInfo().getAlive());
                movieActorDto.setGender(actor.getActorInfo().getGender());
                movieActorDto.setTypeCode(actor.getType().getCode());
                movieActorDto.setTypeCodeDescription(actor.getType().getDescription());
                dto.addActor(movieActorDto);
            });
            finalList.add(dto);
        });
        return finalList.stream().distinct().toList();
    }

    @Override
    public List<MovieBasicInfoDto> getMovieByCommonFactors(String fromActorTypeCode, String toActorTypeCode) {
        List<MovieBasicInfoDto> finalList = new ArrayList<>();
        actorsService.getMovieByCommonFactors(fromActorTypeCode, toActorTypeCode).forEach(item -> {
            MovieBasicInfoDto dto = movieBasicInfoMapper.convert(item);
            dto.setActors(new ArrayList<>());
            item.getActors().forEach(actor -> {
                MovieActorDto movieActorDto = new MovieActorDto();
                movieActorDto.setName(actor.getActorInfo().getFirstName() + " " + actor.getActorInfo().getLastName());
                movieActorDto.setRate(actor.getActorInfo().getRate());
                movieActorDto.setHeight(actor.getActorInfo().getHeight());
                movieActorDto.setAlive(actor.getActorInfo().getAlive());
                movieActorDto.setGender(actor.getActorInfo().getGender());
                movieActorDto.setTypeCode(actor.getType().getCode());
                movieActorDto.setTypeCodeDescription(actor.getType().getDescription());
                dto.addActor(movieActorDto);
            });
            finalList.add(dto);
        });
        return finalList.stream().distinct().toList();
    }
}
