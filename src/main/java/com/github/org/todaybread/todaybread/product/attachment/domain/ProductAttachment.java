package com.github.org.todaybread.todaybread.product.attachment.domain;

import com.github.org.todaybread.todaybread.common.domain.Core;
import com.github.org.todaybread.todaybread.file.domain.File;
import com.github.org.todaybread.todaybread.product.domain.Product;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class ProductAttachment extends Core {

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    Product product;

    @OneToOne(targetEntity = File.class, fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "file_id")
    File file;

    @Builder
    public ProductAttachment(Product product, File file) {
        this.product = product;
        this.file = file;
    }
}
