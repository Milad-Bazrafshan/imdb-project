package org.clw.imdb.dto.movie;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieFilterDto {
    private int from;
    private int size;
    private List<String> actorsName;
    private MovieFilterByActorDto actorFilter;
}
