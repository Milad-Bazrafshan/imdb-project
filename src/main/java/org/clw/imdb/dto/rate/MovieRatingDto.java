package org.clw.imdb.dto.rate;

import lombok.Data;

@Data
public class MovieRatingDto {
    private Long movieId;
    private int rate;
}
