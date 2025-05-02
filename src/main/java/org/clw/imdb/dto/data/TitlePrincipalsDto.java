package org.clw.imdb.dto.data;

import lombok.Data;

@Data
public class TitlePrincipalsDto {
    private String tconst;
    private Integer ordering;
    private String nconst;
    private String category;
    private String jobName;
    private String characters;
}
