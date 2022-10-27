package com.github.org.todaybread.todaybread.product.infra.http.request;

import com.github.org.todaybread.todaybread.product.domain.BreadType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateProductRequest {

    @NotBlank
    String productId;

    String imageId;

    @NotBlank
    String name;

    @NotNull
    BreadType breadType;

    String description;

    @NotNull
    Integer price;
    Integer quantity;

    @Builder
    public UpdateProductRequest(
        String productId,
        String imageId,
        String name,
        BreadType breadType,
        String description,
        Integer price,
        Integer quantity
    ) {
        this.productId = productId;
        this.imageId = imageId;
        this.name = name;
        this.breadType = breadType;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
