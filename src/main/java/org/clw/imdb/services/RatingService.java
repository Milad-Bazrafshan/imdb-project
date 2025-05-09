package org.clw.imdb.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.clw.imdb.model.MovieRating;
import org.clw.imdb.model.repository.MovieRatingRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RatingService {
    private final MovieRatingRepository movieRatingRepository;

    public MovieRating createRating(MovieRating rating) {
        return movieRatingRepository.save(rating);
    }

    public MovieRating updateRating(MovieRating rating) {
        return movieRatingRepository.save(rating);
    }

    public MovieRating getRating(Long id) {
        return movieRatingRepository.findById(id).orElse(null);
    }

    public MovieRating getMovieRate(Long movieId) {
        return movieRatingRepository.findByMovie_Id(movieId);
    }
}
