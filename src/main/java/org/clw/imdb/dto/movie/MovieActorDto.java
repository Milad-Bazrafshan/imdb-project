package org.clw.imdb.dto.movie;

import lombok.Data;
import org.clw.imdb.dto.enums.Gender;

@Data
public class MovieActorDto {
    private String name;
    private Integer age;
    private Gender gender;
    private Boolean alive;
    private String height;
    private Integer rate;
    private String typeCode;
    private String typeCodeDescription;
}
