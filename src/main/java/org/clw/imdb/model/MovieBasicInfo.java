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
@Table(name = "MOVIE_BASIC_INFO")
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

    @Column(nullable = true)
    private String movieYear;

    @Column(nullable = true)
    private Integer averageRate;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private MovieGenre genre;

    /*@JoinColumn
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovieFeature> features = new ArrayList<>();*/

    @JoinColumn
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieActor> actors;

    public void addActor(MovieActor movieActor) {
        if (actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(movieActor);
    }

    public void addRate(Integer rate) {
        if (averageRate == null) {
            averageRate = rate;
        }
        averageRate += rate;
    }
}
