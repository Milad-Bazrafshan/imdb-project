package org.clw.imdb.model.repository;

import org.clw.imdb.model.ActorType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorTypeRepository extends JpaRepository<ActorType, Long> {
    ActorType findByCode(String code);
}