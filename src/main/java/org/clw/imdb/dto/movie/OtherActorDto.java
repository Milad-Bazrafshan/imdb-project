package org.clw.imdb.dto.movie;

import lombok.Data;

@Data
public class OtherActorDto {
    private String type;
    private String name;
    private Boolean alive = Boolean.TRUE;
}
