package org.clw.imdb.dto.movie;

import lombok.Data;

@Data
public class MovieFeatureDto {
    private Long movieId;
    private String typeCode;
    private String value;
}
