package org.clw.imdb.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public class ModelType extends BasicEntity {
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = true)
    private String description;
}
