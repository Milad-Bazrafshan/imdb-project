package org.clw.imdb.model.repository;

import org.clw.imdb.model.SecurityToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecurityTokenRepository extends JpaRepository<SecurityToken, Long> {
    @Query(value = """
            select t from SecurityToken t inner join SecurityToken u\s
            on t.securityUser.id = u.id\s
            where u.id = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<SecurityToken> findAllValidTokenByUser(Long id);

    Optional<SecurityToken> findByToken(String token);
}
