package com.github.org.todaybread.todaybread.store.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface StoreRepository {

    Store save(Store store);

    Optional<Store> getById(String storeId);

    Optional<Store> getByManagerId(String managerId);

    List<Store> getByNameContaining(String search, Pageable pageable);

    void delete(String storeId);
}
