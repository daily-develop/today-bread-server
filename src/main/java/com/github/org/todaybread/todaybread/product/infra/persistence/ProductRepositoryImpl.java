package com.github.org.todaybread.todaybread.product.infra.persistence;

import com.github.org.todaybread.todaybread.file.domain.QFile;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.product.domain.ProductRepository;
import com.github.org.todaybread.todaybread.product.domain.QProduct;
import com.github.org.todaybread.todaybread.store.domain.QStore;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JPAQueryFactory queryFactory;
    private final ProductJpaRepository productJpaRepository;

    private final QProduct product = QProduct.product;
    private final QFile file = QFile.file;
    private final QStore store = QStore.store;

    @Override
    public Optional<Product> getById(String productId) {
        return productJpaRepository.findById(UUID.fromString(productId));
    }

    @Override
    public List<Product> getList(String storeId, String search, Pageable pageable) {
        JPAQuery<Product> query = queryFactory
            .selectFrom(product)
            .leftJoin(product.image, file).fetchJoin()
            .leftJoin(product.store, store).fetchJoin();

        if (!(storeId == null || storeId.isBlank())) {
            query = query.where(product.store.id.eq(UUID.fromString(storeId)));
        }
        if (!(search == null || search.isBlank())) {
            query = query.where(
                product.name.contains(search)
                    .or(product.description.contains(search))
                    .or(product.store.name.contains(search))
            );
        }

        return query
            .orderBy(product.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    @Override
    public Product save(Product product) {
        return productJpaRepository.save(product);
    }
}
