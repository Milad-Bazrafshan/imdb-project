package org.clw.imdb.model.repository;

import org.clw.imdb.model.TitlePrincipals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TitlePrincipalsRepository extends JpaRepository<TitlePrincipals, Long>, JpaSpecificationExecutor<TitlePrincipals> {
}