package org.clw.imdb.model.repository;

import org.clw.imdb.model.MovieBasicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieBasicInfoRepository extends JpaRepository<MovieBasicInfo, Long>, JpaSpecificationExecutor<MovieBasicInfo> {
    List<MovieBasicInfo> findAllByGenre_CodeAndMovieYearOrderByAverageRateDesc(String genreTypeCode, String movieYear);

    @Query("FROM MovieBasicInfo m1 JOIN m1.actors a1, " +
            "MovieBasicInfo m2 JOIN m2.actors a2 " +
            "WHERE (a1.actorInfo.id = a2.actorInfo.id) AND (a1.type.code = :fromActorTypeCode) AND (a2.type.code = :toActorTypeCode)")
    List<MovieBasicInfo> findAllByActors(@Param(value = "fromActorTypeCode") String fromActorTypeCode,
                                         @Param(value = "toActorTypeCode") String toActorTypeCode);
}