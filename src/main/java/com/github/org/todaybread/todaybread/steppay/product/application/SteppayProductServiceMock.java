package com.github.org.todaybread.todaybread.steppay.product.application;

import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayCreateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.request.SteppayUpdateProductRequest;
import com.github.org.todaybread.todaybread.steppay.product.infra.response.SteppayProductResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SteppayProductServiceMock implements SteppayProductService {

    private final Map<Long, SteppayProductResponse> cache = new HashMap<>();

    @Override
    public SteppayProductResponse create(SteppayCreateProductRequest request) {
        SteppayProductResponse response = SteppayProductResponse.builder()
            .id((long) (Math.random() * 10_000))
            .createdAt(LocalDateTime.now())
            .modifiedAt(LocalDateTime.now())
            .code(UUID.randomUUID().toString())
            .type(request.getType())
            .status(request.getStatus())
            .name(request.getName())
            .featuredImageUrl(request.getFeaturedImageUrl())
            .quantity(request.getQuantity())
            .prices(List.of())
            .build();

        cache.put(response.getId(), response);

        return cache.get(response.getId());
    }

    @Override
    public SteppayProductResponse update(Long productId, SteppayUpdateProductRequest request) {
        SteppayProductResponse cached = cache.get(productId);

        cache.put(
            productId,
            SteppayProductResponse.builder()
                .id(productId)
                .createdAt(cached.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .code(cached.getCode())
                .type(cached.getType())
                .status(cached.getStatus())
                .name(request.getName())
                .featuredImageUrl(request.getFeaturedImageUrl())
                .quantity(request.getQuantity())
                .prices(List.of())
                .build()
        );

        return cache.get(productId);
    }

    @Override
    public SteppayProductResponse stop(Long productId) {
        SteppayProductResponse cached = cache.get(productId);

        cache.put(
            productId,
            SteppayProductResponse.builder()
                .id(productId)
                .createdAt(cached.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .code(cached.getCode())
                .type(cached.getType())
                .status("UNSOLD")
                .name(cached.getName())
                .featuredImageUrl(cached.getFeaturedImageUrl())
                .quantity(cached.getQuantity())
                .prices(List.of())
                .build()
        );

        return cache.get(productId);
    }
}
