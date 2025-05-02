package org.clw.imdb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_MOVIE_BASIC_INFO", sequenceName = "SEQ_MOVIE_BASIC_INFO")
public class MovieBasicInfo extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_MOVIE_BASIC_INFO", sequenceName = "SEQ_MOVIE_BASIC_INFO")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String storyline;

    @Column(nullable = false)
    private String taglines;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieGenre genre;

    @JoinColumn(nullable = false)
    @OneToMany(fetch = FetchType.LAZY)
    private List<MovieFeature> features;

    @JoinColumn(nullable = false)
    @OneToMany(fetch = FetchType.LAZY)
    private List<MovieActor> actors;

    public void addActor(MovieActor movieActor) {
        if (actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(movieActor);
    }

    public void addMovieFeature(MovieFeature movieFeature) {
        if (features == null) {
            features = new ArrayList<>();
        }
        features.add(movieFeature);
    }
}
