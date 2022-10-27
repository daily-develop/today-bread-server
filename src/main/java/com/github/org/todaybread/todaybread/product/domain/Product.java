package com.github.org.todaybread.todaybread.product.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.store.domain.Store;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Core {

    @Column(unique = true, nullable = false)
    private int steppayId;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(targetEntity = File.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private File image;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    private int price;

    private int quantity;

    @Builder
    public Product(
        int steppayId,
        Store store,
        File featureImage,
        String name,
        String description,
        BreadType breadType,
        int price,
        int quantity
    ) {
        this.steppayId = steppayId;
        this.store = store;
        this.image = featureImage;
        this.name = name;
        this.description = description;
        this.breadType = breadType;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductResponse toResponse() {
        return ProductResponse.builder()
            .product(this)
            .build();
    }
}
