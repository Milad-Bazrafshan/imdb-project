package org.clw.imdb.dto.actor;

import lombok.Data;

@Data
public class AddMovieActorDto {
    private Long movieId;
    private Long actorId;
    private String typeCode;
}
