package org.clw.imdb.model.repository;

import org.clw.imdb.model.ImdbRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImdbRatingRepository extends JpaRepository<ImdbRating, Long>, JpaSpecificationExecutor<ImdbRating> {
}