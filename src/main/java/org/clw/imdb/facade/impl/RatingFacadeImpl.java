package org.clw.imdb.facade.impl;

import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.dto.filter.ImdbRatingFilterDto;
import org.clw.imdb.dto.rate.MovieRatingDto;
import org.clw.imdb.facade.RatingFacade;
import org.clw.imdb.mapper.MovieRatingMapper;
import org.clw.imdb.mapper.RatingMapper;
import org.clw.imdb.model.MovieBasicInfo;
import org.clw.imdb.model.MovieRating;
import org.clw.imdb.services.MovieService;
import org.clw.imdb.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RatingFacadeImpl implements RatingFacade {
    private final RatingService ratingService;
    @Autowired
    private final RatingMapper ratingMapper;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieRatingMapper movieRatingMapper;

    @Override
    public MovieRatingDto createRating(MovieRatingDto dto) {
        MovieRating movieRating = new MovieRating();
        movieRating.setMovie(movieService.getMovieBasicInfo(dto.getMovieId()));
        movieRating.setRate(dto.getRate());
        ratingService.createRating(movieRating);
        MovieBasicInfo movieBasicInfo = movieService.getMovieBasicInfo(dto.getMovieId());
        movieBasicInfo.addRate(dto.getRate());
        movieService.updateMovieBasicInfo(movieBasicInfo);
        return dto;
    }

    @Override
    public MovieRatingDto getMovieRate(Long movieId) {
        return movieRatingMapper.convert(ratingService.getMovieRate(movieId));
    }

    @Override
    public RatingDto getRating(long id) {
        MovieRating rating = ratingService.getRating(id);
        return ratingMapper.convert(rating);
    }
}
