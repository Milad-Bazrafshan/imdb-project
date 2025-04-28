package org.clw.imdb.facade.impl;

import lombok.RequiredArgsConstructor;
import org.clw.imdb.dto.data.RatingDto;
import org.clw.imdb.dto.filter.ImdbRatingFilterDto;
import org.clw.imdb.facade.RatingFacade;
import org.clw.imdb.mapper.RatingMapper;
import org.clw.imdb.model.ImdbRating;
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

    @Override
    public RatingDto createRating(RatingDto dto) {
        ImdbRating imdbRating = new ImdbRating();
        imdbRating.setTconst(dto.getTconst());
        imdbRating.setAverageRating(dto.getAverageRating());
        imdbRating.setNumVotes(dto.getNumVotes());
        ratingService.createRating(imdbRating);
        return dto;
    }

    @Override
    public List<RatingDto> getRating(ImdbRatingFilterDto filter) {
        Page<ImdbRating> ratingPage = ratingService.getRating(filter);
        return ratingPage.getContent().stream().map(ratingMapper::convert).toList();
    }

    @Override
    public List<ImdbRating> getRatingList(ImdbRatingFilterDto filter) {
        Page<ImdbRating> ratingPage = ratingService.getRating(filter);
        return ratingPage.getContent();
    }

    @Override
    public RatingDto getRating(long id) {
        ImdbRating rating = ratingService.getRating(id);
        return ratingMapper.convert(rating);
    }
}
