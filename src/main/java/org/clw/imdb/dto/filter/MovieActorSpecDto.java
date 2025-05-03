package org.clw.imdb.dto.filter;

import lombok.Builder;
import lombok.Data;
import org.clw.imdb.model.ActorsInfo;
import org.clw.imdb.model.MovieActor;
import org.clw.imdb.model.MovieBasicInfo;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Data
@Builder
public class MovieActorSpecDto {
    List<Specification<ActorsInfo>> actorSpecs;
    List<Specification<MovieBasicInfo>> movieSpecs;
    List<Specification<MovieActor>> movieActorSpecs;
}
