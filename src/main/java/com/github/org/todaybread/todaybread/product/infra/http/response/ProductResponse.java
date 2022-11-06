package com.github.org.todaybread.todaybread.product.infra.http.response;

import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.domain.Product;
import com.github.org.todaybread.todaybread.store.infra.http.response.StoreResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {

    String id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean status;
    StoreResponse store;
    FileResponse image;
    String name;
    List<FileResponse> description;
    BreadType breadType;
    Integer price;
    Integer quantity;

    @Builder
    public ProductResponse(Product product) {
        this.id = product.getId().toString();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
        this.status = product.isStatus();
        this.store = product.getStore().toResponse();
        this.image = product.getImage() != null ? product.getImage().toResponse() : null;
        this.name = product.getName();
        this.description = product.getDescription() != null ?
            product.getDescription()
                .stream()
                .map(it -> it.getFile().toResponse())
                .toList()
            : null;
        this.breadType = product.getBreadType();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}
