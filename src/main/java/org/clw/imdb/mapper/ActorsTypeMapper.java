package org.clw.imdb.mapper;

import org.clw.imdb.dto.actor.ActorsInfoDto;
import org.clw.imdb.dto.movie.TypeDto;
import org.clw.imdb.model.ActorType;
import org.clw.imdb.model.ActorsInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorsTypeMapper {
    TypeDto convert(ActorType actorType);

    ActorType convert(TypeDto typeDto);
}
