package org.clw.imdb.model.repository;

import org.clw.imdb.model.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating, Long>, JpaSpecificationExecutor<MovieRating> {
    MovieRating findByMovie_Id(Long movieId);
}