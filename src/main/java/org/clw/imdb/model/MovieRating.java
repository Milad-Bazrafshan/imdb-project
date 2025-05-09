package org.clw.imdb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "MOVIE_RATING")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_IMDB_RATING", sequenceName = "SEQ_IMDB_RATING")
public class MovieRating extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_IMDB_RATING", sequenceName = "SEQ_IMDB_RATING")
    @Column(name = "ID", nullable = false)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieBasicInfo movie;

    @Column(nullable = false)
    private Integer rate;
}
