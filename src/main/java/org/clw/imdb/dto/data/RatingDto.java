package org.clw.imdb.dto.data;

import lombok.Data;

@Data
public class RatingDto {
    private String tconst;
    private String averageRating;
    private String numVotes;
}
