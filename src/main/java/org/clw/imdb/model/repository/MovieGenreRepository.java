package org.clw.imdb.model.repository;

import org.clw.imdb.model.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
    MovieGenre findByCode(String code);
}