package org.clw.imdb.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.clw.imdb.dto.filter.ImdbRatingFilterDto;
import org.clw.imdb.model.ImdbRating;
import org.clw.imdb.model.ImdbRating_;
import org.clw.imdb.model.repository.ImdbRatingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RatingService {
    private final ImdbRatingRepository imdbRatingRepository;

    public ImdbRating createRating(ImdbRating rating) {
        return imdbRatingRepository.save(rating);
    }

    public ImdbRating updateRating(ImdbRating rating) {
        return imdbRatingRepository.save(rating);
    }

    public ImdbRating getRating(Long id) {
        return imdbRatingRepository.findById(id).orElse(null);
    }

    public Page<ImdbRating> getRating(ImdbRatingFilterDto filter) {
        return imdbRatingRepository.findAll(Specification.allOf(getImdbRatingSpec(filter)), PageRequest.of(filter.getPageNumber(), filter.getPageSize()));
    }

    private List<Specification<ImdbRating>> getImdbRatingSpec(ImdbRatingFilterDto filter) {
        List<Specification<ImdbRating>> specifications = new ArrayList<>();

        if (!ObjectUtils.isEmpty(filter.getAverageRating())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ImdbRating_.AVERAGE_RATING), filter.getAverageRating()));
        }

        if (!ObjectUtils.isEmpty(filter.getTconst())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ImdbRating_.TCONST), filter.getTconst()));
        }

        if (!ObjectUtils.isEmpty(filter.getNumVotes())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ImdbRating_.NUM_VOTES), filter.getNumVotes()));
        }

        if (!ObjectUtils.isEmpty(filter.getId())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ImdbRating_.ID), filter.getId()));
        }

        return specifications;
    }
}
