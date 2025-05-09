package org.clw.imdb.mapper;

import org.clw.imdb.dto.movie.MovieBasicInfoDto;
import org.clw.imdb.model.MovieBasicInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieBasicInfoMapper {
    MovieBasicInfoDto convert(MovieBasicInfo movieBasicInfo);

    MovieBasicInfo convert(MovieBasicInfoDto movieBasicInfoDto);
}
