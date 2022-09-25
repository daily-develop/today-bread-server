package com.github.org.todaybread.todaybread.auth.infra.persistence.auth;

import com.github.org.todaybread.todaybread.auth.domain.auth.Auth;
import com.github.org.todaybread.todaybread.auth.domain.auth.AuthRepository;
import com.github.org.todaybread.todaybread.auth.domain.auth.AuthType;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthJpaRepository authJpaRepository;


    @Override
    public Optional<Auth> getById(String id) {
        return authJpaRepository.findById(UUID.fromString(id));
    }

    @Override
    public Optional<Auth> getByMemberId(String memberId) {
        return authJpaRepository.findByMemberId(UUID.fromString(memberId));
    }

    @Override
    public Optional<Auth> getByAuthTypeAndClientId(AuthType type, String clientId) {
        return authJpaRepository.findByTypeAndClientId(type, clientId);
    }

    @Override
    public Auth save(Auth auth) {
        return authJpaRepository.save(auth);
    }
}
