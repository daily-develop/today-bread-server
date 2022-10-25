package com.github.org.todaybread.todaybread.product.domain;

import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    private int id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(targetEntity = File.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_image_id")
    private File image;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    private int price;

    @Builder
    public Product(
        int id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Store store,
        File featureImage,
        String name,
        String description,
        BreadType breadType,
        Integer price
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.store = store;
        this.image = featureImage;
        this.name = name;
        this.description = description;
        this.breadType = breadType;
        this.price = price;
    }

    public ProductResponse toResponse() {
        return ProductResponse.builder()
            .product(this)
            .build();
    }
}
