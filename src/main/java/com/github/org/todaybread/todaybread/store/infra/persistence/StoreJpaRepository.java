package com.github.org.todaybread.todaybread.store.infra.persistence;

import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface StoreJpaRepository extends JpaRepository<Store, UUID> {

    List<Store> findByNameContaining(String search, Pageable pageable);
}
