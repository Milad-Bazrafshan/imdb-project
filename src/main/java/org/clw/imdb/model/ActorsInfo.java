package org.clw.imdb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.clw.imdb.dto.enums.Gender;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_ACTORS_INFO", sequenceName = "SEQ_ACTORS_INFO")
public class ActorsInfo extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_ACTORS_INFO", sequenceName = "SEQ_ACTORS_INFO")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Boolean alive;

    @Column
    private String height;

    @Column
    private String fatherName;

    @Column
    private Integer age;

    @Column
    private Integer rate;
}
