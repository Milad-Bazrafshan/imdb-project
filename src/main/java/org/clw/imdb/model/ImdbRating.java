package org.clw.imdb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "IMDB_RATING")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_IMDB_RATING", sequenceName = "SEQ_IMDB_RATING")
public class ImdbRating extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_IMDB_RATING", sequenceName = "SEQ_IMDB_RATING")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String tconst;
    @Column(nullable = false)
    private String averageRating;
    @Column(nullable = false)
    private String numVotes;
}
