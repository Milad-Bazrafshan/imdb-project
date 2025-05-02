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
@SequenceGenerator(name = "SEQ_BLOB_FILE_TYPE", sequenceName = "SEQ_BLOB_FILE_TYPE")
public class BlobFileModelType extends ModelType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_BLOB_FILE_TYPE", sequenceName = "SEQ_BLOB_FILE_TYPE")
    @Column(name = "ID", nullable = false)
    private Long id;
}
