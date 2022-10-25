package com.github.org.todaybread.todaybread.store.domain;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {

    Store save(Store store);

    Optional<Store> getById(String storeId);

    Optional<Store> getByManagerId(String managerId);

    List<Store> findByNameContaining(String search, Pageable pageable);

    void delete(String storeId);
}
