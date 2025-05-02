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
@SequenceGenerator(name = "SEQ_MOVIE_FEATURE", sequenceName = "SEQ_MOVIE_FEATURE")
public class MovieFeature extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_MOVIE_FEATURE", sequenceName = "SEQ_MOVIE_FEATURE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieFeatureType type;

    @Column(nullable = false)
    private String value;
}
