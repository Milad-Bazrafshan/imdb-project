package org.clw.imdb.dto.movie;

import lombok.Data;

@Data
public class MovieFilterDto {
    private int from;
    private int size;
    private MovieFilterByActorDto actorFilter;
}
