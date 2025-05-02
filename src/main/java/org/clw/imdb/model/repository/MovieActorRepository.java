package org.clw.imdb.model.repository;

import org.clw.imdb.model.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieActorRepository extends JpaRepository<MovieActor, Long> {
}