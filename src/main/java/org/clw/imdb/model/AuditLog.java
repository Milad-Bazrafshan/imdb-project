package org.clw.imdb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.clw.imdb.dto.enums.Gender;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "SEQ_AUDIT_LOG", sequenceName = "SEQ_AUDIT_LOG")
public class AuditLog extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_AUDIT_LOG", sequenceName = "SEQ_AUDIT_LOG")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    private String uri;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private LocalDate date = LocalDate.now();
}
