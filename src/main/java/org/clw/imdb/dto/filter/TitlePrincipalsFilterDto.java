package org.clw.imdb.dto.filter;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class TitlePrincipalsFilterDto {
    private Long id;
    private String tconst;
    private Integer ordering;
    private String nconst;
    private String category;
    private String jobName;
    private String characters;
    @Default
    private int pageNumber = 0;
    @Default
    private int pageSize = 20;
}
