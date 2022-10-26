package com.github.org.todaybread.todaybread.store.infra.persistence;

import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

interface StoreJpaRepository extends JpaRepository<Store, UUID> {

    Optional<Store> findByManagerId(UUID managerId);
}
