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
@SequenceGenerator(name = "SEQ_BLOB_FILE", sequenceName = "SEQ_BLOB_FILE")
public class BlobFile extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_BLOB_FILE", sequenceName = "SEQ_BLOB_FILE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private BlobFileModelType type;

    @Column(nullable = false)
    private String base64;

    @Lob
    @Column(nullable = false)
    private String lob;
}
