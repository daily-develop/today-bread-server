package com.github.org.todaybread.todaybread.auth.infra.persistence.auth;

import com.github.org.todaybread.todaybread.auth.domain.auth.Auth;
import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthJpaRepository extends JpaRepository<Auth, UUID> {

    Optional<Auth> findByMemberId(UUID memberId);

    Optional<Auth> findByTypeAndClientId(AuthType type, String clientId);
}
