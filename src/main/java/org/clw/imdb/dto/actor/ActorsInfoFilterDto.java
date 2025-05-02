package org.clw.imdb.dto.actor;

import lombok.Builder;
import lombok.Data;
import org.clw.imdb.dto.enums.Gender;

@Data
@Builder
public class ActorsInfoFilterDto {
    private int from;
    private int size;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Boolean alive;
    private String fromHeight;
    private String toHeight;
    private Integer age;
    private Integer fromAge;
    private Integer toAge;
}
