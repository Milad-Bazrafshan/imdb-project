package org.clw.imdb.dto.data;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TitleAkasDto {
    private String titleId;
    private int ordering;
    private String title;
    private String region;
    private String language;
    private List<TitleAkasType> types;
    private List<?> attributes;
    private Boolean isOriginalTitle;
}
