package com.github.org.todaybread.todaybread.product.infra.http.request;

import com.github.org.todaybread.todaybread.product.domain.BreadType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CreateProductRequest {

    @NotBlank
    String managerId;

    @NotNull
    MultipartFile image;

    @NotBlank
    String name;

    @NotNull
    BreadType breadType;

    @NotBlank
    String description;
    
    int price;
    int quantity;

    @Builder
    public CreateProductRequest(
        String managerId,
        MultipartFile image,
        String name,
        BreadType breadType,
        String description,
        int price,
        int quantity
    ) {
        this.managerId = managerId;
        this.image = image;
        this.name = name;
        this.breadType = breadType;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
