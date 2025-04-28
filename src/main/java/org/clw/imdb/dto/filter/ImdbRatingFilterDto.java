package org.clw.imdb.dto.filter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImdbRatingFilterDto {
    private Long id;
    private String tconst;
    private String averageRating;
    private String numVotes;
    private int pageNumber = 0;
    private int pageSize = 20;
}
