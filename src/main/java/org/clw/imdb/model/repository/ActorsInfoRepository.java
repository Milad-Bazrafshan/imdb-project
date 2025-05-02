package org.clw.imdb.model.repository;

import org.clw.imdb.model.ActorsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActorsInfoRepository extends JpaRepository<ActorsInfo, Long>, JpaSpecificationExecutor<ActorsInfo> {
}