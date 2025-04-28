package org.clw.imdb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SECURITY_TOKEN")
public class SecurityToken {
    @Id
    @GeneratedValue
    public Long id;
    @Column(unique = true)
    public String token;
    public boolean revoked;
    public boolean expired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECURITY_USER")
    public SecurityUser securityUser;
}
