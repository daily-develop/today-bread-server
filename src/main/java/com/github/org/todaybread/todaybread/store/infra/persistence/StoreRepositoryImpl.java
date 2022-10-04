package com.github.org.todaybread.todaybread.store.infra.persistence;

import com.github.org.todaybread.todaybread.store.domain.Store;
import com.github.org.todaybread.todaybread.store.domain.StoreRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

    private final StoreJpaRepository storeRepository;

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Optional<Store> getById(String storeId) {
        return storeRepository.findById(UUID.fromString(storeId));
    }

    @Override
    public List<Store> findByNameContaining(String search, Pageable pageable) {
        return storeRepository.findByNameContaining(search, pageable);
    }

    @Override
    public void delete(String storeId) {
        storeRepository.deleteById(UUID.fromString(storeId));
    }

}
