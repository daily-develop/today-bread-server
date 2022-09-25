package com.github.org.todaybread.todaybread.auth.domain.auth;

import java.util.Optional;

public interface AuthRepository {
    
    Optional<Auth> getById(String id);

    Optional<Auth> getByMemberId(String memberId);

    Optional<Auth> getByAuthTypeAndClientId(AuthType type, String clientId);

    Auth save(Auth auth);
}
