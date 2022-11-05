package com.github.org.todaybread.todaybread.product.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.product.attachment.domain.ProductAttachment;
import com.github.org.todaybread.todaybread.product.infra.http.response.ProductResponse;
import com.github.org.todaybread.todaybread.store.domain.Store;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Core {

    @Column(unique = true, nullable = false)
    private Integer steppayId;

    @ColumnDefault("true")
    private boolean status;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(targetEntity = File.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private File image;

    private String name;

    @OneToMany(targetEntity = ProductAttachment.class, mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductAttachment> description;

    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    private Integer price;

    private Integer quantity;

    @Builder
    public Product(
        Integer steppayId,
        Store store,
        File featureImage,
        String name,
        List<ProductAttachment> description,
        BreadType breadType,
        Integer price,
        Integer quantity
    ) {
        this.steppayId = steppayId;
        this.status = true;
        this.store = store;
        this.image = featureImage;
        this.name = name;
        this.description = description;
        this.breadType = breadType;
        this.price = price;
        this.quantity = quantity;
    }

    public Product updateStatus(boolean status) {
        this.status = status;

        return this;
    }

    public Product updateImage(File image) {
        this.image = image;

        return this;
    }

    public Product updateName(String name) {
        this.name = name;

        return this;
    }

    public Product updateDescription(List<ProductAttachment> description) {
        this.description = description;

        return this;
    }

    public Product updateBreadType(BreadType breadType) {
        this.breadType = breadType;

        return this;
    }

    public Product updatePrice(Integer price) {
        this.price = price;

        return this;
    }

    public Product updateQuantity(Integer quantity) {
        this.quantity = quantity;

        return this;
    }

    public ProductResponse toResponse() {
        return ProductResponse.builder()
            .product(this)
            .build();
    }
}
