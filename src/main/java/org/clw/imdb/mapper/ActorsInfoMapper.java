package org.clw.imdb.mapper;

import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.movie.MovieFeatureTypeDto;
import org.clw.imdb.model.ActorsInfo;
import org.clw.imdb.model.MovieFeatureType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorsInfoMapper {
    ActorsInfoDto convert(ActorsInfo actorsInfo);

    ActorsInfo convert(ActorsInfoDto actorsInfoDto);
}
