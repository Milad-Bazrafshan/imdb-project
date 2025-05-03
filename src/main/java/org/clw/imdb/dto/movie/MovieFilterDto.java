package org.clw.imdb.dto.movie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieFilterDto {
    private int from;
    private int size;
    private MovieFilterByActorDto actorFilter;
}
