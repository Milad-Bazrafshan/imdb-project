package org.clw.imdb.dto.movie;

import lombok.Data;

import java.util.List;

@Data
public class MovieFilterByActorDto {
    private String directorFirstName;
    private String writerFirstName;
    private List<OtherActorDto> otherActors;
    private Boolean sharedVideos;
    private Boolean alive = Boolean.TRUE;
}
