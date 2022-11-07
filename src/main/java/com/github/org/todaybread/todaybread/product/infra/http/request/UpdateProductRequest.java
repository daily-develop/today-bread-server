package com.github.org.todaybread.todaybread.product.infra.http.request;

import com.github.org.todaybread.todaybread.product.domain.BreadType;
import java.util.List;
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

    List<String> description;

    @NotNull
    Long price;
    Long quantity;

    @Builder
    public UpdateProductRequest(
        String productId,
        String imageId,
        String name,
        BreadType breadType,
        List<String> description,
        Long price,
        Long quantity
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
