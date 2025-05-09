package org.clw.imdb.facade.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.clw.imdb.dto.actor.AddMovieActorDto;
import org.clw.imdb.dto.movie.*;
import org.clw.imdb.exception.ActorNotFoundException;
import org.clw.imdb.exception.ActorTypeException;
import org.clw.imdb.exception.FeaturesTypeNotFoundException;
import org.clw.imdb.exception.MovieGenreNotFoundException;
import org.clw.imdb.facade.MovieFacade;
import org.clw.imdb.mapper.MovieBasicInfoMapper;
import org.clw.imdb.mapper.MovieBasicInfoMapperImpl;
import org.clw.imdb.mapper.MovieFeatureTypeMapper;
import org.clw.imdb.mapper.MovieGenreMapper;
import org.clw.imdb.model.*;
import org.clw.imdb.services.ActorsService;
import org.clw.imdb.services.MovieService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieFacadeImpl implements MovieFacade {
    private final MovieService movieService;
    private final MovieGenreMapper movieGenreMapper;
    private final MovieFeatureTypeMapper movieFeatureTypeMapper;
    private final ActorsService actorsService;
    private final MovieBasicInfoMapper movieBasicInfoMapper;

    @Override
    public void createMovie(MovieDto movieDto) {
        MovieBasicInfo basicInfo = new MovieBasicInfo();
        basicInfo.setTitle(movieDto.getTitle());
        basicInfo.setDescription(movieDto.getDescription());
        basicInfo.setTaglines(movieDto.getTaglines());
        basicInfo.setStoryline(movieDto.getStoryline());
        basicInfo.setMovieYear(movieDto.getYear());

        MovieGenre movieGenre = movieService.getMovieGenre(movieDto.getGenreTypeCode());
        if (ObjectUtils.isEmpty(movieGenre)) {
            throw new MovieGenreNotFoundException();
        }

        basicInfo.setGenre(movieGenre);

        if (!ObjectUtils.isEmpty(movieDto.getActors())) {
            basicInfo.setActors(this.checkAndGetActorList(movieDto.getActors()));
        }

/*        if (!ObjectUtils.isEmpty(movieDto.getFeatures())) {
            basicInfo.setFeatures(this.checkAndGetFeaturesList(movieDto.getFeatures()));
        }*/

        movieService.createMovieBasicInfo(basicInfo);
    }

    private List<MovieActor> checkAndGetActorList(List<AddMovieActorDto> actorsList) {
        List<MovieActor> finalList = new ArrayList<>();
        actorsList.forEach(actor -> {
            MovieActor movieActor = new MovieActor();
            ActorType actorType = actorsService.getActorType(actor.getTypeCode());
            if (ObjectUtils.isEmpty(actorType)) throw new ActorTypeException();

            ActorsInfo actorsInfo = actorsService.getActorsInfo(actor.getActorId());
            if (ObjectUtils.isEmpty(actorsInfo)) throw new ActorNotFoundException();

            movieActor.setType(actorType);
            movieActor.setActorInfo(actorsInfo);
            finalList.add(movieActor);
        });

        return finalList;
    }

    private List<MovieFeature> checkAndGetFeaturesList(List<MovieFeatureDto> featureList) {
        List<MovieFeature> finalList = new ArrayList<>();
        featureList.forEach(item -> {
            MovieFeature movieFeature = new MovieFeature();
            MovieFeatureType movieFeatureType = movieService.getMovieFeatureType(item.getTypeCode());
            if (ObjectUtils.isEmpty(movieFeatureType)) throw new FeaturesTypeNotFoundException();

            movieFeature.setType(movieFeatureType);
            movieFeature.setValue(item.getValue());
            finalList.add(movieFeature);
        });

        return finalList;
    }

    @Override
    public void createMovieGenre(MovieGenreDto movieGenre) {
        movieService.createMovieGenre(movieGenreMapper.convert(movieGenre));
    }

    @Override
    public void createGroupMovieGenre(List<MovieGenreDto> list) {
        for (MovieGenreDto movieGenreDto : list) {
            MovieGenre movieGenre = movieGenreMapper.convert(movieGenreDto);
            movieService.createMovieGenre(movieGenre);
        }
    }

    @Override
    public MovieGenreDto getMovieGenre(String code) {
        MovieGenre movieGenre = movieService.getMovieGenre(code);
        return movieGenreMapper.convert(movieGenre);
    }

    @Override
    public List<MovieGenreDto> getAllMovieGenre() {
        return movieService.getMovieGenre().stream().map(movieGenreMapper::convert).toList();
    }

    @Override
    public void createMovieFeatureType(MovieFeatureTypeDto movieFeatureTypeDto) {
        movieService.createMovieFeatureType(movieFeatureTypeMapper.convert(movieFeatureTypeDto));
    }

    @Override
    public void createGroupMovieFeatureType(List<MovieFeatureTypeDto> list) {
        for (MovieFeatureTypeDto dto : list) {
            MovieFeatureType movieFeatureType = movieFeatureTypeMapper.convert(dto);
            movieService.createMovieFeatureType(movieFeatureType);
        }
    }

    @Override
    public MovieFeatureTypeDto getMovieFeatureType(String code) {
        MovieFeatureType movieFeatureType = movieService.getMovieFeatureType(code);
        return movieFeatureTypeMapper.convert(movieFeatureType);
    }

    @Override
    public List<MovieFeatureTypeDto> getAllMovieFeatureType() {
        return movieService.getAllMovieFeatureType().stream().map(movieFeatureTypeMapper::convert).toList();
    }

    @Override
    public List<MovieBasicInfoDto> getTopMovieByGenre(String genreTypeCode, String year) {
        return movieService.getTopAverageRateByGenreAndYear(genreTypeCode, year).stream().map(movieBasicInfoMapper::convert).toList();
    }
}
