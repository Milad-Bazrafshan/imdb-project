package org.clw.imdb.model.repository;

import org.clw.imdb.model.MovieFeatureType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieFeatureTypeRepository extends JpaRepository<MovieFeatureType, Long> {
    MovieFeatureType findByCode(String code);
}