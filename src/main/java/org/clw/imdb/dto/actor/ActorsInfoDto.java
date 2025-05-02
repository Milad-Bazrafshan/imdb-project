package org.clw.imdb.dto.actor;

import lombok.Data;
import org.clw.imdb.dto.enums.Gender;

@Data
public class ActorsInfoDto {
    private String firstName;
    private String lastName;
    private String fatherName;
    private Gender gender;
    private Boolean alive;
    private String height;
    private Integer age;
}
