package org.clw.imdb.model.repository;

import org.clw.imdb.model.MovieBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieBasicInfoRepository extends JpaRepository<MovieBasicInfo, Long>, JpaSpecificationExecutor<MovieBasicInfo> {
}