package org.clw.imdb.mapper;

import org.clw.imdb.dto.data.TitlePrincipalsDto;
import org.clw.imdb.model.TitlePrincipals;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TitlePrincipalsMapper {
    TitlePrincipalsDto convert(TitlePrincipals titlePrincipals);

    TitlePrincipals convert(TitlePrincipalsDto dto);
}
