package org.clw.imdb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_MOVIE_GENRE", sequenceName = "SEQ_MOVIE_GENRE")
public class MovieGenre extends ModelType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_MOVIE_GENRE", sequenceName = "SEQ_MOVIE_GENRE")
    @Column(name = "ID", nullable = false)
    private Long id;
}
