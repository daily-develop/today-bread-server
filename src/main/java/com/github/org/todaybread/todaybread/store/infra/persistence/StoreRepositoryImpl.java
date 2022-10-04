package com.github.org.todaybread.todaybread.store.infra.persistence;

import com.github.org.todaybread.todaybread.manager.domain.QManager;
import com.github.org.todaybread.todaybread.store.domain.QStore;
import com.github.org.todaybread.todaybread.store.domain.Store;
import com.github.org.todaybread.todaybread.store.domain.StoreRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    private final JPAQueryFactory queryFactory;
    private final QStore store = QStore.store;
    private final QManager manager = QManager.manager;

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
        return queryFactory
            .selectFrom(store)
            .leftJoin(store.manager, manager).fetchJoin()
            .where(
                contains(search)
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    @Override
    public void delete(String storeId) {
        storeRepository.deleteById(UUID.fromString(storeId));
    }

    private BooleanExpression contains(String search) {
        return search == null || search.isBlank() ? null : store.name.contains(search);
    }
}