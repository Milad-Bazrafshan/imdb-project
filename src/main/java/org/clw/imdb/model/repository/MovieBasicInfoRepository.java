package org.clw.imdb.model.repository;

import org.clw.imdb.model.MovieBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieBasicInfoRepository extends JpaRepository<MovieBasicInfo, Long> {
}