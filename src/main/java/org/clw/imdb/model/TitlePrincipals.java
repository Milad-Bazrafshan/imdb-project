package org.clw.imdb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "TITLE_PRINCIPALS")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_TITLE_PRINCIPALS", sequenceName = "SEQ_TITLE_PRINCIPALS")
public class TitlePrincipals extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_TITLE_PRINCIPALS", sequenceName = "SEQ_TITLE_PRINCIPALS")
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(nullable = true)
    private String tconst;
    @Column(nullable = true)
    private Integer ordering;
    @Column(nullable = true)
    private String nconst;
    @Column(nullable = true)
    private String category;
    @Column(nullable = true)
    private String jobName;
    @Column(nullable = true)
    private String characters;
}
