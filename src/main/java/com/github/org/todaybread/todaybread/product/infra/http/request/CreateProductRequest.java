package com.github.org.todaybread.todaybread.product.infra.http.request;

import com.github.org.todaybread.todaybread.product.domain.BreadType;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CreateProductRequest {

    @NotBlank
    String storeId;

    MultipartFile image;

    @NotBlank
    String name;

    @NotNull
    BreadType breadType;

    List<MultipartFile> description;

    @NotNull
    Long price;
    Long quantity;

    @Builder
    public CreateProductRequest(
        String storeId,
        MultipartFile image,
        String name,
        BreadType breadType,
        List<MultipartFile> description,
        Long price,
        Long quantity
    ) {
        this.storeId = storeId;
        this.image = image;
        this.name = name;
        this.breadType = breadType;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
}
