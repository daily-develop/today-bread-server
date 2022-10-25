package com.github.org.todaybread.todaybread.product.infra.http.response;

import com.github.org.todaybread.todaybread.file.infra.http.response.FileResponse;
import com.github.org.todaybread.todaybread.product.domain.BreadType;
import com.github.org.todaybread.todaybread.product.domain.Product;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {

    int id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    FileResponse image;
    String name;
    String description;
    BreadType breadType;
    Integer price;

    @Builder
    public ProductResponse(Product product) {
        this.id = product.getId();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
        this.image = product.getImage() != null ? product.getImage().toResponse() : null;
        this.name = product.getName();
        this.description = product.getDescription();
        this.breadType = product.getBreadType();
        this.price = product.getPrice();
    }
}
