package org.clw.imdb.dto.movie;

import lombok.Data;

@Data
public class MovieFilterByActorDto {
    private String directorFirstName;
    private String writerFirstName;
    private boolean sharedVideos;
}
