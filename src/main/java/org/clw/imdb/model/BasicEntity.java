package org.clw.imdb.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BasicEntity implements Serializable {
    @Version
    @Column(name = "VERSION_", nullable = false)
    private Timestamp version = new Timestamp(System.currentTimeMillis());
    @Column(name = "CREATEDAT_", nullable = false)
    private Date createdAt = new Date();
    @Column(name = "UNIQ_", nullable = false)
    private String uniq = UUID.randomUUID().toString().replace("-", "");
}
